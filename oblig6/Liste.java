interface Liste<T> {
    public int stoerrelse();
    public void leggTil(int pos, T x);
    public void leggTil(T x);
    public void sett(int pos, T x);
    public T hent(int pos);
    public void hentAlle();
    public T fjern(int pos);
    public T fjern();
    public boolean inneholder(T x);
    public T fjernInnhold(T x);
    public void toem();
}
