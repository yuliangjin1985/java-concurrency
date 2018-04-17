package com.javferna.packtpub.mastering.knn.main;

import java.util.Date;
import java.util.List;

import com.javferna.packtpub.mastering.knn.data.BankMarketing;
import com.javferna.packtpub.mastering.knn.loader.BankMarketingLoader;
import com.javferna.packtpub.mastering.knn.parallel.individual.KnnClassifierParallelIndividual;

/**
 * Main class that launches the tests using the fine-grained concurrent version and concurrent sorting
 * @author author
 *
 */
public class ParallelIndividualMainSort {

	public static void main(String[] args) {

		BankMarketingLoader loader = new BankMarketingLoader();
		List<BankMarketing> train = loader.load(DataUtil.BankPath);
		System.out.println("Train: " + train.size());
		List<BankMarketing> test = loader.load(DataUtil.TestPath);
		System.out.println("Test: " + test.size());
		double currentTime = 0.0;
		int testSize = test.size();
		int success = 0, mistakes = 0;
		
		int k = 10;
		if (args.length==1) {
			k = Integer.parseInt(args[0]);
		}
		List<BankMarketing> marketings = test.subList(0, testSize);
		success = 0;
		mistakes = 0;
		KnnClassifierParallelIndividual classifier = new KnnClassifierParallelIndividual(
				train, k, 1, true);
		try {
			Date start, end;
			start = new Date();
			for (BankMarketing example : marketings) {
				String tag = classifier.classify(example);
				if (tag.equals(example.getTag())) {
					success++;
				} else {
					mistakes++;
				}
			}
			end = new Date();

			currentTime = end.getTime() - start.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		classifier.destroy();
		System.out.println("Parallel Classifier Individual by train data - K: " + k
				+ " - Factor 1 - Parallel Sort: true");
		System.out.println("Success: " + success);
		System.out.println("Mistakes: " + mistakes);
		System.out.println("Execution Time: " + (currentTime / 1000)
				+ " seconds.");

	}

}
