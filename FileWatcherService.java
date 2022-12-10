import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.*;

public class FileWatcherService {
    public FileWatcherService() {
        try {
            WatchService service = FileSystems.getDefault().newWatchService();

            Path path = Paths.get("../BaseDatos");

            path.register(service, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey key = null;
            while ((key = service.take()) != null) {
                /*for (WatchEvent<?> event : key.pollEvents()) {
                    System.out.println("Tipo evento: "+ event.kind() + " Archivo: "+ event.context());

                }*/

                key.reset();
                ConfiguradorBD.leerArchivoConfiguracion();
            }
            System.out.println("saliiiiiiiiii");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
