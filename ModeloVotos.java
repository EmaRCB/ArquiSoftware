
public class ModeloVotos extends Modelo {

    private final Integer[] votos;

    public ModeloVotos(){
        data = new Integer[]{0,0,0};
        votos = (Integer[]) data;
        this.id = "modeloVotos";
    }


    @Override
    public void service(Object data) {
        switch ((Integer) data){
            case 1 -> votos[0]++;
            case 2 -> votos[1]++;
            case 3 -> votos[2]++;
        }
    }
}
