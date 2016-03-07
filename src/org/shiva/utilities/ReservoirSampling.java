package org.shiva.utilities;

import java.util.Random;

public class ReservoirSampling {
	private static int[] result;
	
	public static int[] selectKSamplesS(int[] stream, int k) {
		result = new int[k];
		for (int i = 0; i < k; i++) {
			result[i] = stream[i];
		}
		Random random = new Random();
		for (int i = k; i < stream.length; i++) {
			int j = random.nextInt(i + 1);
			if (j < k) {
				int temp = result[j];
				result[j] = stream[i];
				stream[i] = temp;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
