import java.io.IOException;
import java.nio.file.*;
import java.sql.SQLException;
import java.util.List;

public class PoolObserver {
    public PoolObserver() {
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();

            Path path = Paths.get("configuracion");

            path.register(watcher,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            while(true){
                Thread.sleep( 50 );
                WatchKey key;
                Thread.sleep( 50 );
                key = watcher.take();
                Thread.sleep( 50 );
                List<WatchEvent<?>> listaEventos = key.pollEvents();
                for (WatchEvent<?> evento : listaEventos){

                    WatchEvent.Kind<?> tipoEvento = evento.kind();
                    Path fileName = (Path)evento.context();
                    //System.out.println(tipoEvento.name() + ":" + fileName);

                }
                key.reset();
                ConfiguradorBD.leerArchivoConfiguracion();
                CreadorConexionBD.realizarConexion();
                ConfiguradorBD.pedir();

            }




        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
