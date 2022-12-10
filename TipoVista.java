public class TipoVista {
    private String type;
    private Object view;

    public TipoVista(String type, Object view) {
        this.type = type;
        this.view = view;
    }

    public String getType() {
        return type;
    }

    public Object getView() {
        return view;
    }
}
