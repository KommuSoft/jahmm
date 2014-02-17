package jahmm.draw;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author kommusoft
 * @param <TInput> The type of input expected by the drawer.
 */
public interface DrawerDot<TInput> {

    /**
     * Writes a dot file depicting the given TInput.
     *
     * @param input The HMM to depict.
     * @param filename The resulting 'dot' file filename.
     * @throws java.io.IOException If the filename is invalid, not effective or
     * the user has no proper access to write to the file.
     */
    void write(TInput input, String filename) throws IOException;

    /**
     * Writes a dot file depicting the given TInput.
     *
     * @param input The HMM to depict.
     * @param stream The stream to write the 'dot' stream to.
     * @throws java.io.IOException If the stream is not accessible or not
     * effective.
     */
    void write(TInput input, OutputStream stream) throws IOException;

    /**
     * Writes a dot file depicting the given TInput.
     *
     * @param input The HMM to depict.
     * @param streamWriter The writer to write the 'dot' stream to.
     * @throws java.io.IOException If the writer is not accessible or not
     * effective.
     */
    void write(TInput input, OutputStreamWriter streamWriter) throws IOException;

}
