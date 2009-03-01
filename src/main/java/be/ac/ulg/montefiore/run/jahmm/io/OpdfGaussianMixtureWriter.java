package be.ac.ulg.montefiore.run.jahmm.io;

import java.io.IOException;
import java.io.Writer;

import be.ac.ulg.montefiore.run.jahmm.OpdfGaussianMixture;


/**
 * This class implements a {@link OpdfGaussianMixture} writer.  It is
 * compatible with the {@link OpdfGaussianMixtureReader} class.
 */
public class OpdfGaussianMixtureWriter
extends OpdfWriter<OpdfGaussianMixture>
{
	public void write(Writer writer, OpdfGaussianMixture opdf)
	throws IOException
	{
		writer.write("GaussianMixtureOPDF [ ");
		
		write(writer, opdf.means());
		writer.write(" ");
		write(writer, opdf.variances());
		writer.write(" ");
		write(writer, opdf.proportions());
		
		writer.write(" ]");
	}
}
