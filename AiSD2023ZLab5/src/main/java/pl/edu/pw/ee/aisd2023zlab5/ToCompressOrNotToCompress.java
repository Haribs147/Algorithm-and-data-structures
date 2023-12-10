package pl.edu.pw.ee.aisd2023zlab5;

public class ToCompressOrNotToCompress {
    private String filePathRead;
    private String filePathWrite;

    public ToCompressOrNotToCompress(String[] args) {
        if (args.length != 2) {
            throw new RuntimeException("Podaj plik wejściowy do kompresji i wyjściowy jaki ma być skompresowany, albo wejściowy do dekompresji i wyjściowy jaki ma być zdekompresowany");
        }
        if (args[0].equals(args[1])) {
            throw new RuntimeException("Plik wejściowy i wyjściowy nie mogą się tak samo nazywać");
        }
        filePathRead = args[0];
        filePathWrite = args[1];
    }

    public String getFilePathRead() {
        return filePathRead;
    }

    public String getFilePathWrite() {
        return filePathWrite;
    }

    public int checkIfCompressAndChangeFileNames() {
        int decompress = 0;
        int lastDotIndex = filePathRead.lastIndexOf('.');
        if (lastDotIndex != -1) {
            String prefix;
            if (filePathRead.substring(lastDotIndex + 1).equals("comp")) {
                decompress = 1;
            } else {
                lastDotIndex = filePathWrite.lastIndexOf('.');
                if (lastDotIndex != -1) {
                    prefix = filePathWrite.substring(0, lastDotIndex + 1);
                    filePathWrite = prefix + "comp";
                } else {
                    filePathWrite = filePathWrite + ".comp";
                }
            }
        } else {
            lastDotIndex = filePathWrite.lastIndexOf('.');
            if (lastDotIndex != -1) {
                String prefix = filePathWrite.substring(0, lastDotIndex + 1);
                filePathWrite = prefix + "comp";
            } else {
                filePathWrite = filePathWrite + ".comp";
            }
        }
        return decompress;
    }
}
