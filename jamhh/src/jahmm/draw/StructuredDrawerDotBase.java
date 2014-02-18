package jahmm.draw;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 *
 * @author kommusoft
 * @param <TInput> The type of objects the drawer draws.
 */
public abstract class StructuredDrawerDotBase<TInput> extends DrawerDotBase<TInput> {

    /**
     * The beginning of the structured file.
     * @return The beginning of the structured file.
     * @note By default, the value is an opening accolade.
     */
    protected String beginning() {
        return "{";
    }

    /**
     * The ending of the structure file.
     * @return The ending of the structured file.
     * @note By default, the value is a closing accolade.
     */
    protected String ending() {
        return "}";
    }

    /**
     * Writes a dot file depicting the given input.
     *
     * @param input The object to draw.
     * @param streamWriter The writer to write the 'dot' stream to.
     * @throws java.io.IOException If the stream writer is not accessible or not
     * effective.
     */
    @Override
    public void write(TInput input, OutputStreamWriter streamWriter) throws IOException {
        streamWriter.write(this.beginning());
        this.innerWrite(input, streamWriter);
        streamWriter.write(this.ending());
    }

    /**
     * Writes the inner content.
     *
     * @param input The object to draw.
     * @param streamWriter The writer to write the 'dot' stream to.
     * @throws java.io.IOException if the stream writer is not accessible or not
     * effective.
     */
    protected abstract void innerWrite(TInput input, OutputStreamWriter streamWriter) throws IOException;

}
