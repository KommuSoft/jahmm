package jahmm.draw;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public abstract class DrawerDotBase<TInput> implements DrawerDot<TInput> {

    @Override
    public void write(TInput input, String filename) throws IOException {
        try (FileWriter fw = new FileWriter(filename)) {
            this.write(input, fw);
        }
    }

    @Override
    public void write(TInput input, OutputStream stream) throws IOException {
        try (OutputStreamWriter osw = new OutputStreamWriter(stream)) {
            this.write(input, osw);
        }
    }

}
