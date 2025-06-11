public abstract class Zona implements Comparable<Zona> {
    private String nome;

    public Zona(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract String relatorio();

    @Override
    public int compareTo(Zona outra) {
        return this.nome.compareToIgnoreCase(outra.getNome());
    }
}
