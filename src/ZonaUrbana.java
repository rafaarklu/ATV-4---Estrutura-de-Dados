import java.util.ArrayList;
import java.util.List;

public class ZonaUrbana extends Zona implements Emergencia {
    private List<Sensor> sensores = new ArrayList<>();

    public ZonaUrbana(String nome) {
        super(nome);
    }

    public void adicionarSensor(Sensor s) {
        sensores.add(s);
    }

    public double calcularTotal() {
        return sensores.stream().mapToDouble(Sensor::getValorAQI).sum();
    }

    public double calcularMedia() {
        return sensores.isEmpty() ? 0 : calcularTotal() / sensores.size();
    }

    @Override
    public String classificarNivelEmergencia() {
        double media = calcularMedia();
        if (media <= 50) return "Sem risco";
        else if (media <= 100) return "Monitoramento intensificado";
        else if (media <= 150) return "Alerta para grupos sensíveis";
        else if (media <= 200) return "Alerta Amarelo";
        else if (media <= 300) return "Alerta Laranja";
        else return "Alerta Vermelho (emergência total)";
    }

    @Override
    public String relatorio() {
        String rel = "Zona: " + getNome() +
                     "\nTotal semanal: " + String.format("%.2f", calcularTotal()) +
                     "\nMédia semanal: " + String.format("%.2f", calcularMedia()) +
                     "\nNível de emergência: " + classificarNivelEmergencia();
        if (classificarNivelEmergencia().contains("Vermelho")) {
            rel += "\n>>> Ação imediata recomendada: evacuação ou restrição de atividades externas.";
        }
        return rel;
    }
}
