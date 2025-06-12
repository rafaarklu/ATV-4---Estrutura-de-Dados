import javax.swing.*;
import java.util.*;

public class Main {
    private static Set<Zona> zonas = new TreeSet<>();

    public static void main(String[] args) {
        String menu = """
                Escolha uma opção:
                1 - Registrar Zona
                2 - Adicionar sensor
                3 - Imprimir relatório
                4 - Finalizar
                """;
        int opcao = 0;

        do {
            String input = JOptionPane.showInputDialog(null, menu, "Menu Principal", JOptionPane.PLAIN_MESSAGE);
            if (input == null) break; 

            try {
                opcao = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um número válido de 1 a 4.");
                continue;
            }

            switch (opcao) {
                case 1 -> registrarZona();
                case 2 -> adicionarSensor();
                case 3 -> imprimirRelatorio();
                case 4 -> JOptionPane.showMessageDialog(null, "Finalizando...");
                default -> JOptionPane.showMessageDialog(null, "Opção inválida. Digite um número de 1 a 4.");
            }
        } while (opcao != 4);
    }

    private static void registrarZona() {
        String nome = JOptionPane.showInputDialog(null, "Nome da zona:");
        if (nome == null || nome.isBlank()) return;

        String tipoStr = JOptionPane.showInputDialog(null, "Digite o tipo da zona:\n1 - Urbana\n2 - Rural");
        if (tipoStr == null || tipoStr.isBlank()) return;

        int tipo;
        try {
            tipo = Integer.parseInt(tipoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite 1 para Urbana ou 2 para Rural.");
            return;
        }

        if (tipo == 1) zonas.add(new ZonaUrbana(nome));
        else if (tipo == 2) zonas.add(new ZonaRural(nome));
        else JOptionPane.showMessageDialog(null, "Opção inválida. Digite 1 para Urbana ou 2 para Rural.");
    }

    private static void adicionarSensor() {
        String nome = JOptionPane.showInputDialog(null, "Nome da zona:");
        if (nome == null || nome.isBlank()) return;

        Zona zona = buscarZona(nome);
        if (zona instanceof ZonaUrbana urbana) {
            String id = JOptionPane.showInputDialog(null, "ID do sensor:");
            if (id == null || id.isBlank()) return;

            String data = JOptionPane.showInputDialog(null, "Data da coleta:");
            if (data == null || data.isBlank()) return;

            String valorStr = JOptionPane.showInputDialog(null, "Valor AQI:");
            if (valorStr == null || valorStr.isBlank()) return;

            try {
                double valor = Double.parseDouble(valorStr);
                urbana.adicionarSensor(new Sensor(id, data, valor));
                JOptionPane.showMessageDialog(null, "Sensor adicionado com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor AQI inválido!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Somente zonas urbanas aceitam sensores.");
        }
    }

    private static void imprimirRelatorio() {
        String nome = JOptionPane.showInputDialog(null, "Nome da zona:");
        if (nome == null || nome.isBlank()) return;

        Zona zona = buscarZona(nome);
        if (zona != null) {
            StringBuilder relatorio = new StringBuilder();
            relatorio.append("=== RELATÓRIO DE EMERGÊNCIA AMBIENTAL ===\n");
            relatorio.append(zona.relatorio());

            JOptionPane.showMessageDialog(null, relatorio.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Zona não encontrada.");
        }
    }

    private static Zona buscarZona(String nome) {
        return zonas.stream().filter(z -> z.getNome().equalsIgnoreCase(nome)).findFirst().orElse(null);
    }
}
