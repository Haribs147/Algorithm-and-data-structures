package pl.edu.pw.ee.aisd2023zlab5;

import java.io.*;


public class Main {

    public static void main(String[] args) throws IOException {
        ToCompressOrNotToCompress fileNames = new ToCompressOrNotToCompress(args);
        int decompress = fileNames.checkIfCompressAndChangeFileNames();
        String filePathRead = fileNames.getFilePathRead();
        String filePathWrite = fileNames.getFilePathWrite();
        if(decompress == 0) {
            Compressor compressor = new Compressor(filePathRead);
            compressor.compressTheFile(compressor.dictionaryEncoding(), filePathWrite, filePathRead);
        }
        else {
            Decompressor decompressor = new Decompressor(new HuffTree());
            decompressor.decompress(filePathRead, filePathWrite);
        }
    }
}
