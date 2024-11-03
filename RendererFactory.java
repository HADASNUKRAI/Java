/**
 * Responsible to map the string to the renderer interface
 */
public class RendererFactory {
    public RendererFactory(){}
    public Renderer buildRenderer(String type, int size){
        if (type.equals("console") || type.equals("Console") || type.equals("CONSOLE")){
            return new ConsoleRenderer(size);
        }

        return new VoidRenderer();
    }
}
