package de.nightmoon.boompixel;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Admin on 11.06.2017.
 */
public class FlatWorldGenerator extends ChunkGenerator {

    public void setBlock(byte[][] result, int x, int y, int z, byte blkid) {
        if (result[y >> 4] == null) {
            result[y >> 4] = new byte[4096];
        }
        result[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = blkid;
    }

    @Override
    public byte[][] generateBlockSections(World world, Random random, int x, int z, BiomeGrid biomes) {
        byte[][] blocks = new byte[256 / 16][];

        int xL, yL, zL;

        for(xL = 0; xL < 16; xL++) {
            for(zL = 0; zL < 16; zL++) {
                setBlock(blocks, xL, 0, zL, (byte) Material.BEDROCK.getId());
            }
        }

        for(xL = 0; xL < 16; xL++) {
            for(zL = 0; zL < 16; zL++) {
                for(yL = 1; yL <= 100; yL++) {
                    setBlock(blocks, xL, yL, zL, (byte) Material.STONE.getId());
                }
            }
        }

        //byte[][] result = new byte[world.getMaxHeight() / 16][];

        return blocks;
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return new LinkedList<BlockPopulator>();
    }
}
