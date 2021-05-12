public class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> {

    @Override
    public void leggTil(T x) {
        if (stoerrelse() == 0) {
            super.leggTil(x);
            return;
        }

        for (int i = 0; i < stoerrelse(); i++) {
            if (hent(i).compareTo(x) > 0) {
                super.leggTil(i, x);
                return;
            }
        }

        super.leggTil(x);
    }

    @Override
    public T fjern() {
        if (stoerrelse() == 1) return super.fjern();
        return fjern(stoerrelse() - 1);
    }

    @Override
    public void sett(int pos, T x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggTil(int pos, T x) {
        throw new UnsupportedOperationException();
    }
}
