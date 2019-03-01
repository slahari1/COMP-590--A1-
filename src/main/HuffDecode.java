package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import io.InputStreamBitSource;
import io.InsufficientBitsLeftException;

public class HuffDecode {

	public static void main(String[] args) throws FileNotFoundException {
		String input_file_name = "data/recompressed.dat";
		String output_file_name = "data/reuncompressed.txt";
		
		FileInputStream fis = new FileInputStream(input_file_name);

		InputStreamBitSource bit_source = new InputStreamBitSource(fis);

		List<SymbolWithCodeLength> symbols_with_length = new ArrayList<SymbolWithCodeLength>();

		// Read in huffman code lengths from input file and associate them with each symbol as a 
		// SymbolWithCodeLength object and add to the list symbols_with_length.
		
		int code_length;
		int value;
		SymbolWithCodeLength sym_length;
		try {
			for (int i = 0; i < 256; i++){
				code_length = bit_source.next(8);
				value = i;
				sym_length = new SymbolWithCodeLength(value, code_length);
				symbols_with_length.add(sym_length);
			}
		} catch (InsufficientBitsLeftException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	
		
		// Then sort they symbols.
		
		Collections.sort(symbols_with_length);
		for(int j=0; j<256; j++){
			System.out.println(symbols_with_length.get(j).codeLength());
		}
		

		// Now construct the canonical huffman tree

		HuffmanDecodeTree huff_tree = new HuffmanDecodeTree(symbols_with_length);

		int num_symbols = 0;

			// Read in the next 32 bits from the input file the number of symbols
			try {
				num_symbols = bit_source.next(32);
				System.out.println(num_symbols);
			} catch (InsufficientBitsLeftException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		try {

			// Open up output file.
			
			FileOutputStream fos = new FileOutputStream(output_file_name);

			for (int i=0; i<num_symbols; i++) {
				// Decode next symbol using huff_tree and write out to file.
				try {
					fos.write(huff_tree.decode(bit_source));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// Flush output and close files.
			
			fos.flush();
			fos.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}