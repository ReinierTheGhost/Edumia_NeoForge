package com.legends.edumia.world.map;

import com.legends.edumia.Edumia;
import com.legends.edumia.utils.EdumiaLog;
import com.legends.edumia.utils.resources.FileType;
import com.legends.edumia.utils.resources.FileUtils;
import com.legends.edumia.world.biomes.surface.MapBasedBiomePool;
import com.legends.edumia.world.biomes.surface.MapBasedCustomBiome;
import com.legends.edumia.world.chunkgen.map.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class EdumiaMapGeneration {
    public static int CURRENT_ITERATION = 0;

    private FileUtils fileUtils;
    private static final int WATER_BUFFER = 16;
    private static final float WATER_HEIGHT_MULTIPLIER = 1.0f;

    private static BufferedImage baseHeightImage;
    private static BufferedImage edgeHeightImage;
    private static BufferedImage initialMap;

    public EdumiaMapGeneration() throws Exception {
        fileUtils = FileUtils.getInstance();
        generate();
    }

    public void generate() throws Exception {
        EdumiaLog.logInfoMsg("");
        EdumiaLog.logInfoMsg("================ EdumiaMapGeneration ================");

        try{
            initialMap = getInitialImage();
            if(initialMap == null){
                throw new Exception(this + " : The image of the map in resource has created an error and operation cannot continue.");
            }
        } catch (Exception e){
            EdumiaLog.logError("MiddleEarthMapGeneration::generate() - Fetch Initial Map", e);
        }

        if(!Edumia.ENABLE_INSTANT_BOOTING){
            EdumiaLog.logInfoMsg("Instant Booting - Disabled");
        }
        else {
            EdumiaLog.logInfoMsg("Instant Booting - Enabled");
            boolean pasteSuccess = true;

            // check if copy&paste is necessary
            File rootFolder = new File(EdumiaMapConfigs.MOD_DATA_ROOT);
            File modRootFolder = new File(EdumiaMapConfigs.MOD_DATA_MOD_ROOT);
            File destFolder = new File(EdumiaMapConfigs.MOD_DATA);
            rootFolder.mkdirs();
            modRootFolder.mkdirs();
            destFolder.mkdirs();

            if(destFolder.list().length == 0) { // Instant Booting triggered
                try {
                    String resourceFolderPath = "/%s".formatted(EdumiaMapConfigs.INITIAL_MAP_FOLDER);
                    String runtimeFolderPath = "%s".formatted(EdumiaMapConfigs.MOD_DATA_MOD_ROOT);

                    InputStream inputStream = getClass().getResourceAsStream(resourceFolderPath + ".zip");
                    ZipInputStream zipInputStream = new ZipInputStream(inputStream);
                    ZipEntry entry;

                    File runtimeDataVersionDirectory = new File(runtimeFolderPath);
                    runtimeDataVersionDirectory.mkdirs();
                    byte[] buffer = new byte[1024];

                    while ((entry = zipInputStream.getNextEntry()) != null) {
                        File entryDestination = new File(runtimeFolderPath, entry.getName());
                        if (entry.isDirectory()) {
                            entryDestination.mkdirs();
                        } else {
                            entryDestination.getParentFile().mkdirs();
                            FileOutputStream fos = new FileOutputStream(entryDestination);
                            int len;
                            while ((len = zipInputStream.read(buffer)) > 0) {
                                fos.write(buffer, 0, len);
                            }
                            fos.close();
                        }
                    }

                    inputStream.close();
                    zipInputStream.close();
                } catch (IOException e){
                    EdumiaLog.logError("MiddleEarthMapGeneration::Couldn't copy paste folders", e);
                    pasteSuccess = false;
                }

                if(pasteSuccess) {
                    EdumiaLog.logInfoMsg("Instant Booting - Completed");
                    return;
                } else {
                    EdumiaLog.logError("Instant Booting - Failure");
                }
            } else {
                EdumiaLog.logInfoMsg("Instant Booting - Skipped, Files already present");
                EdumiaLog.logInfoMsg("Validating data content...");
            }

        }

        EdumiaLog.logInfoMsg("Validating initial map BIOME colors;");
        if(!validateBaseColors(initialMap)) return;

        EdumiaLog.logInfoMsg("Validating BIOME generation availability;");
        int iterationToGenerate = (EdumiaMapConfigs.FORCE_GENERATION)
                ? EdumiaMapConfigs.MAP_ITERATION + 1
                : findAmountOfIterationToGenerate(initialMap);

        if(iterationToGenerate > 0){
            EdumiaLog.logInfoMsg("Begin BIOME generation;");
            generateBiomes(initialMap, iterationToGenerate);
        }

        EdumiaLog.logInfoMsg("Validating initial map HEIGHT MODIFIER generation availability;");
        if(!validateBaseHeightDatas()){
            EdumiaLog.logInfoMsg("Begin initial map HEIGHT MODIFIER generation;");
            generateBaseHeightImage(initialMap);
            generateEdgeHeightImage(initialMap);
        }

        EdumiaLog.logInfoMsg("Validating HEIGHT generation availability;");
        if(!validateHeightDatas(initialMap)){
            EdumiaLog.logInfoMsg("Begin HEIGHT generation;");
            generateHeight(initialMap);
        }
    }

    public static BufferedImage getEdgeHeightImage(){
        return edgeHeightImage;
    }

    private boolean validateBaseColors(BufferedImage initialMap) {
        if (initialMap == null){
            return false;
        }
        for(int x = 0; x < initialMap.getWidth(); x++){
            for(int y = 0; y < initialMap.getWidth(); y++){
                try{
                    MapBasedBiomePool.getBiomeByColor(initialMap.getRGB(x,y));
                } catch (Exception e) {
                    EdumiaLog.logError("EdumiaMapGeneration::Cannot find color at [%s,%s] in the inital map".formatted(x,y));
                    return false;
                }
            }
        }
        return true;
    }

    private BufferedImage getInitialImage(){
        EdumiaLog.logInfoMsg("Validating ORIGINAL image existence;");
        BufferedImage initialImage = fileUtils.getResourceImage(EdumiaMapConfigs.INITIAL_IMAGE);
        if(initialImage == null){
            EdumiaLog.logError("Initial map image couldn't be found!");
            return null;
        }
        EdumiaLog.logInfoMsg("Validating ORIGINAL image size;");
        if(initialImage.getWidth() % EdumiaMapConfigs.REGION_SIZE != 0 ||
                initialImage.getHeight() % EdumiaMapConfigs.REGION_SIZE != 0){
            EdumiaLog.logError("Initial map image has the wrong size!");
            return null;
        }

        return initialImage;
    }

    private int findAmountOfIterationToGenerate(BufferedImage initialMap) {
        if (initialMap == null){
            return 0;
        }
        int currentRegionAmountX = initialMap.getWidth() / EdumiaMapConfigs.REGION_SIZE;
        int currentRegionAmountY = initialMap.getHeight() / EdumiaMapConfigs.REGION_SIZE;
        int absoluteMapIteration = EdumiaMapConfigs.MAP_ITERATION + 1;

        for(int i = 0; i < absoluteMapIteration; i++){
            if(i > 0){
                currentRegionAmountX *= 2;
                currentRegionAmountY *= 2;
            }

            for(int x = 0; x < currentRegionAmountX; x ++){
                for(int y = 0; y < currentRegionAmountY; y ++) {
                    String path = EdumiaMapConfigs.BIOME_PATH.formatted(i) + EdumiaMapConfigs.IMAGE_NAME.formatted(x,y);
                    if(fileUtils.getRunImage(path) == null){
                        EdumiaLog.logError("Need to regenerate biome files: Lacking biome file at : [%s]".formatted(path));
                        return absoluteMapIteration - i;
                    }
                }
            }
        }
        return 0;
    }

    private BufferedImage[][][] generateBiomes(BufferedImage initialImage, int missingIterationAmount) {
        int startingIteration = EdumiaMapConfigs.MAP_ITERATION + 1 - missingIterationAmount;
        if(startingIteration == 0){
            generateInitialBiomes(initialImage);
            startingIteration ++;
        }
        for(int i = startingIteration; i < EdumiaMapConfigs.MAP_ITERATION + 1; i ++){
            ExecutorService executorService = Executors.newFixedThreadPool(EdumiaMapConfigs.THREAD_POOL_SIZE);

            CURRENT_ITERATION = i;

            int regionAmountX = (int) (initialImage.getWidth() / EdumiaMapConfigs.REGION_SIZE * Math.pow(2, i - 1));
            int regionAmountY = (int) (initialImage.getHeight() / EdumiaMapConfigs.REGION_SIZE * Math.pow(2, i - 1));

            for(int x = 0; x < regionAmountX; x++){
                for(int y = 0; y < regionAmountY; y++){
                    int finalI = i;
                    int finalX = x;
                    int finalY = y;
                    executorService.submit(() -> {
                        String path = EdumiaMapConfigs.BIOME_PATH.formatted(finalI - 1) +
                                EdumiaMapConfigs.IMAGE_NAME.formatted(finalX, finalY);
                        BufferedImage[][] subidivedRegions = ImageUtils.subdivide(fileUtils.getRunImage(path));

                        for(int j = 0; j < 2; j ++){
                            for(int k = 0; k < 2; k ++){
                                fileUtils.saveImage(
                                        subidivedRegions[j][k],
                                        EdumiaMapConfigs.BIOME_PATH.formatted(finalI),
                                        EdumiaMapConfigs.IMAGE_NAME.formatted((finalX * 2) + j, (finalY * 2) + k),
                                        FileType.Png
                                );
                            }
                        }
                    });
                }
            }
            executorService.shutdown();
            try {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (Exception e) {
                EdumiaLog.logError("Error while generating biomes");
            }
        }
        return new BufferedImage[0][][];
    }

    private void generateInitialBiomes(BufferedImage initialImage){
        if(initialImage.getWidth() != EdumiaMapConfigs.REGION_SIZE || initialImage.getWidth() !=  EdumiaMapConfigs.REGION_SIZE){
            EdumiaLog.logError("Need to regenerate height files: Need splitting for the initial image!");
            for(int i = 0; i < initialImage.getWidth() / EdumiaMapConfigs.REGION_SIZE; i++){
                for(int j = 0; j < initialImage.getHeight() / EdumiaMapConfigs.REGION_SIZE; j++){
                    BufferedImage newImage = initialImage.getSubimage(EdumiaMapConfigs.REGION_SIZE * i, EdumiaMapConfigs.REGION_SIZE * j, EdumiaMapConfigs.REGION_SIZE, EdumiaMapConfigs.REGION_SIZE);
                    fileUtils.saveImage(newImage, EdumiaMapConfigs.BIOME_PATH.formatted(0), EdumiaMapConfigs.IMAGE_NAME.formatted(i,j), FileType.Png);
                }
            }
        } else {
            fileUtils.saveImage(initialImage, EdumiaMapConfigs.BIOME_PATH.formatted(0), EdumiaMapConfigs.IMAGE_NAME.formatted(0,0), FileType.Png);
        }
    }

    private boolean validateHeightDatas(BufferedImage initialImage) {
        int regionAmountX = (int) (initialImage.getWidth() / EdumiaMapConfigs.REGION_SIZE * Math.pow(2, EdumiaMapConfigs.MAP_ITERATION));
        int regionAmountY = (int) (initialImage.getHeight() / EdumiaMapConfigs.REGION_SIZE * Math.pow(2, EdumiaMapConfigs.MAP_ITERATION));

        for(int x = 0; x < regionAmountX; x ++){
            for(int y = 0; y < regionAmountY; y ++) {
                String path = EdumiaMapConfigs.HEIGHT_PATH + EdumiaMapConfigs.IMAGE_NAME.formatted(x,y);
                if(fileUtils.getRunImage(path) == null){
                    return false;
                }
            }
        }
        return true;
    }

    private final static int HEIGHT_BLUR_SIZE = 17;
    private void generateHeight(BufferedImage initialImage) {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(EdumiaMapConfigs.THREAD_POOL_SIZE);

        int regionAmountX = (int) (initialImage.getWidth() / EdumiaMapConfigs.REGION_SIZE * Math.pow(2, EdumiaMapConfigs.MAP_ITERATION));
        int regionAmountY = (int) (initialImage.getHeight() / EdumiaMapConfigs.REGION_SIZE * Math.pow(2, EdumiaMapConfigs.MAP_ITERATION));

        for(int x = 0; x < regionAmountX; x++) {
            for (int y = 0; y < regionAmountY; y++) {
                int finalX = x;
                int finalY = y;
                executorService.submit(() -> {
                    fileUtils.saveImage(
                            ImageUtils.blur(processHeightRegion(fileUtils.getRunImageWithBorders(finalX, finalY, HEIGHT_BLUR_SIZE),
                                            EdumiaMapConfigs.REGION_SIZE, true, finalX, finalY, HEIGHT_BLUR_SIZE),
                                    HEIGHT_BLUR_SIZE, true),
                            EdumiaMapConfigs.HEIGHT_PATH,
                            EdumiaMapConfigs.IMAGE_NAME.formatted(finalX, finalY),
                            FileType.Png
                    );
                });
            }
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (Exception e) {
            EdumiaLog.logError("Error while generating heights");
        }

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        EdumiaLog.logInfoMsg("TIME BLUR FOR HEIGHT: " + timeElapsed);
    }

    private boolean validateBaseHeightDatas() {
        String path = EdumiaMapConfigs.BASE_HEIGHT_PATH + EdumiaMapConfigs.BASE_HEIGHT_IMAGE_NAME;
        if(fileUtils.getRunImage(path) == null){
            return false;
        }
        baseHeightImage = fileUtils.getRunImage(path);
        return true;
    }

    private final static int BASE_HEIGHT_BLUR_SIZE = 25;
    private void generateBaseHeightImage(BufferedImage initialMap) {
        baseHeightImage = ImageUtils.blur(processHeightRegion(initialMap, EdumiaMapConfigs.REGION_SIZE, false, 0,0, 0), BASE_HEIGHT_BLUR_SIZE, false);
        fileUtils.saveImage(baseHeightImage,
                EdumiaMapConfigs.BASE_HEIGHT_PATH,
                EdumiaMapConfigs.BASE_HEIGHT_IMAGE_NAME,
                FileType.Png
        );
    }

    private void generateEdgeHeightImage(BufferedImage initialMap){
        edgeHeightImage = ImageUtils.edge(initialMap);
        edgeHeightImage = ImageUtils.blur(edgeHeightImage, 15, false);
        fileUtils.saveImage(edgeHeightImage,
                EdumiaMapConfigs.BASE_HEIGHT_PATH,
                EdumiaMapConfigs.BASE_EDGE_IMAGE_NAME,
                FileType.Png);
    }

    private static BufferedImage processHeightRegion(BufferedImage biomeImage, int size, boolean hasBaseImage, int imageX, int imageZ, int brushSize) {
        BufferedImage newHeightRegion = new BufferedImage(size + brushSize*2, size + brushSize*2, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < size + brushSize*2; x++) {
            for (int z = 0; z < size + brushSize*2; z++) {
                try {
                    MapBasedCustomBiome biome = MapBasedBiomePool.getBiomeByColor(biomeImage.getRGB(x, z));
                    int height = biome.getHeight();
                    if(height > 255){
                        height = 255;
                    }

                    int waterHeightDifference = biome.getWaterHeight() - MapBasedCustomBiome.DEFAULT_WATER_HEIGHT;
                    int water = 0;
                    int waterHeight = height - waterHeightDifference;
                    if(waterHeight < 0) {
                        water = (int) Math.abs((waterHeight * WATER_HEIGHT_MULTIPLIER) - WATER_BUFFER);
                        height = Math.max(0, height);
                    }

                    short noiseModifier = (short) (biome.getBiomeData().noiseModifier * 127);

                    Color heightModifier = (hasBaseImage)
                            ? getBaseImageHeightModifier(x, z, imageX, imageZ, brushSize)
                            : new Color(Math.abs(height), noiseModifier, 0);

                    int red = (int)Math.round((biome.getBiomeData().heightModifier * ((double)Math.abs(height)) + (1 - biome.getBiomeData().heightModifier) * (double)heightModifier.getRed()));

                    int green = (int)((noiseModifier + heightModifier.getRed()) / 2f);


                    Color newColor = new Color(red, green, water);

                    newHeightRegion.setRGB(x, z, newColor.getRGB());
                } catch (Exception e) {
                    throw new RuntimeException("EdumiaMapGeneration.processHeightRegion : Failed to create color for the height [%s]".formatted(e));
                }
            }
        }

        return newHeightRegion;
    }

    private static Color getBaseImageHeightModifier(int x, int z, int imageX, int imageZ, int brushSize) {
        double iterationDivider = Math.pow(2, EdumiaMapConfigs.MAP_ITERATION);

        float posX = (float) (((imageX * EdumiaMapConfigs.REGION_SIZE) + x - brushSize) / iterationDivider);
        float posZ = (float) (((imageZ * EdumiaMapConfigs.REGION_SIZE) + z - brushSize) / iterationDivider);

        int baseX = (int) (posX);
        int baseZ = (int) (posZ);

        Color initialColor = getColorFromBaseMap(baseX, baseZ);
        Color rightColor = getColorFromBaseMap(baseX + 1, baseZ);
        Color bottomColor = getColorFromBaseMap(baseX, baseZ + 1);
        Color botRightColor = getColorFromBaseMap(baseX + 1, baseZ + 1);

        float weightX = posX - baseX;
        float weightZ = posZ - baseZ;

        Color interpolatedTopColor = interpolateColor(initialColor, rightColor, weightX);
        Color interpolatedBottomColor = interpolateColor(bottomColor, botRightColor, weightX);

        return interpolateColor(interpolatedTopColor, interpolatedBottomColor, weightZ);
    }

    private static Color getColorFromBaseMap(int x, int z){
        if(x < 0 || x >= EdumiaMapConfigs.REGION_SIZE || z < 0 || z >= EdumiaMapConfigs.REGION_SIZE) {
            x = 0; // default biome corner
            z = 0;
        }
        return new Color(baseHeightImage.getRGB(x, z));
    }

    private static Color interpolateColor(Color color1, Color color2, float weight) {
        weight = Math.abs(weight);
        float newWeight = 1.0f - weight;

        int red = (int)(newWeight * color1.getRed() + weight * color2.getRed());
        int green = (int)(newWeight * color1.getGreen() + weight * color2.getGreen());
        int blue = (int)(newWeight * color1.getBlue() + weight * color2.getBlue());

        try{
            return new Color(red, green, blue);
        } catch (Exception e){
            EdumiaLog.logError(e.getMessage());
            return color1;
        }
    }

}

