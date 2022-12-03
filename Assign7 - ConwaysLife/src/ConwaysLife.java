// Reference for Lanterna 3: https://github.com/mabe02/lanterna/blob/master/docs/contents.md
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.w3c.dom.Text;

public class ConwaysLife {
    public static void main(String[] args) {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            Screen screen = new TerminalScreen(terminal);
            TextGraphics graphics = screen.newTextGraphics();

            TerminalSize size = screen.getTerminalSize();
            LifeSimulator simulation = new LifeSimulator(size.getColumns(), size.getRows());

            screen.startScreen();
            screen.setCursorPosition(null);


            LifeSimulator mySim = new LifeSimulator(200, 200);

            mySim.insertPattern(new PatternBlock(), 0, 5);
            mySim.insertPattern(new PatternBlock(), 9, 12);
            mySim.insertPattern(new PatternBlinker(), 7, 10);
            mySim.insertPattern(new PatternGlider(), 17, 23);
            mySim.insertPattern(new PatternAcorn(), 12, 30);
            mySim.insertPattern(new PatternAcorn(),20,59);
            mySim.insertPattern(new PatternGlider(),13,42);

            for (int i = 0; i < 150; i++) {
                render(mySim, screen, graphics);   // Render the current state of the simulation
                //sampleRender(screen, graphics, i);
                Thread.yield();                         // Let the JVM have some time to update other things
                Thread.sleep(300);                // Sleep for a bit to make for a nicer paced animation
                mySim.update();                    // Tell the simulation to update
            }
            screen.stopScreen();
        } catch (Exception ex) {
            System.out.println("Something bad happened: " + ex.getMessage());
        }
    }

    public static void render(LifeSimulator simulation, Screen screen, TextGraphics graphics) {
        screen.clear();
        for (int row = 0; row < simulation.getSizeX(); row++) {
            for (int col = 0; col < simulation.getSizeY(); col++) {
                if (simulation.getCell(row, col)) {
                    graphics.setCharacter(col, row, 'X');
                }
                else {
                    graphics.setCharacter(col, row, '|');
                }
            }
        }

        try {
            screen.refresh();
        } catch (Exception ex) {

        }
    }
}
