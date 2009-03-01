package be.ac.ulg.montefiore.run.jahmm.apps.cli;

import be.ac.ulg.montefiore.run.jahmm.apps.cli.CommandLineArguments.Arguments;


/**
 * This class implements an action that prints a usage message.
 */
public class HelpActionHandler extends ActionHandler
{
	public void act()
	{
		String s = "Usage:\tCli (create|print|learn-kmeans|" +
		"learn-bw|generate|distance-kl) <arguments>\n" +
		"\tCli -help\n";
		
		s += "\nCommand line interface for the Jahmm library.\nThe '-help'" +
		"argument prints this help.\n";
		
		s += "The 'create' option creates a HMM and write it to file. " +
		"'print' reads a HMM\nfrom a file and prints it in a human " +
		"readable way.  'learn-kmeans' (resp.\n'learn-bw') applies the " +
		"k-means (resp. Baum-Welch) learning algorithm.\n'generate' creates" +
		"sequences of observation from a HMM. 'distance-kl' computes a\n" +
		"stochastic Kullback-Leibler distance between two HMMs.\n";
		
		s += "\nMore information can be found at:\n" +
		"http://www.run.montefiore.ulg.ac.be/~francois/software/jahmm/cli/\n";
		
		s += "\nArguments:\n";
		s += "-opdf [integer|gaussian|gaussian_mixture|multi_gaussian]\n" +
		"\tDetermines the observation distribution type associated with the\n" +
		"\tstates of the HMM.\n";
		
		s += "-r <range>\n\tThe 'range' option is mandatory when using\n" +
		"\tdistributions dealing with integers; <range> is a number such\n" +
		"\tthat the distribution is related to numbers in the range\n" +
		"\t0, 1, ..., range-1.\n";
		
		s += "-ng <number>\n\tThis option is mandatory when using gaussian " +
		"mixture\n\tdistribution.  It  determines the number of gaussians.\n";
		
		s += "-d <dimension>\n\tThis option is mandatory when using " +
		"multi-variate gaussian\n\tdistributions. It determines the " +
		"dimension of the observation\n\tvectors.\n";
		
		s += "-n <nb_states>\n\tThe number of states of the HMM.\n";
		s += "-i <input_file>\n\tAn HMM input file.  Default is standard " +
		"input.\n";
		s += "-o <output_file>\n\tAn HMM output file.  Default is standard " +
		"output.\n";
		
		s += "-os <output_file>\n\tA sequences output file.\n  Default is " +
		"standard output.\n";
		s += "-is <input_file>\n\tA sequences input file.\n";
		
		s += "-ikl <input_file>\n\tAn HMM input file with respect to which " +
		"a Kullback-Leibler distance can\n\tbe computed.\n";
		
		s += "-ni <nb>\n\tThe number of iterations performed by the " +
		"Baum-Welch algorithm.  Default is " +
		Arguments.NB_ITERATIONS.getDefault() + ".\n";
		
		s += "All input (resp. output) file names can be replaced by '-' " +
		"to mean using\nstandard input (resp. output).\n";
		
		System.out.println(s);
	}
}
