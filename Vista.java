public abstract class Vista {

    protected Controlador controlador;

    public void setController(Controlador controlador) {
        this.controlador = controlador;
    }

    public abstract void update(Modelo modelo);

    public abstract void initView();

}
