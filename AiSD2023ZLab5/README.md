## Huffman Encoding Compressor and Decompressor

### Description

This project contains implementations of a file compressor and decompressor for text files using the Huffman coding algorithm and a priority queue based on a binary heap. The Huffman algorithm is a popular algorithm for lossless data compression, which utilizes binary tree to encode characters based on their frequency of occurrence. The program automatically detects whether compression or decompression is performed based on the file extension. If the file has an extension other than "*.comp", compression is performed; otherwise, decompression is performed.

### How to Use

#### File Compression:
- Run the program with two command-line arguments.
- The first argument is the name of the input file you want to compress.
- The second argument is the name of the output file where the compressed data will be saved.
- The script will compress the input file using the Huffman algorithm and save the compressed data to the output file. The output file will have a new extension "*.comp".

#### File Decompression:
- Run the program with two command-line arguments.
- The first argument is the name of the compressed file you want to decompress.
- The second argument is the name of the output file where the decompressed data will be saved.
- The script will decompress the compressed file using the Huffman algorithm and restore the original text file. The output file will have the extension "*.txt".

### Requirements

This project requires a JDK of 8 or higher.

