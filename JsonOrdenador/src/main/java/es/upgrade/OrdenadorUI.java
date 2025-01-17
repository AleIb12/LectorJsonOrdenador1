package es.upgrade;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class OrdenadorUI extends JFrame {
    private JTextArea textArea;

    public OrdenadorUI() {
        setTitle("Detalles del Ordenador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }


    /**
     * Displays the detailed information of a computer in a text area.
     * The information includes all components of the computer such as:
     * - Basic details (name and price)
     * - Processor(s)
     * - Motherboard(s)
     * - RAM modules
     * - Hard drives
     * - Power supply units
     * - Graphics cards
     * - Peripherals (keyboard, mouse, monitor)
     * 
     * Each component's information is displayed in a formatted section if it exists.
     * The output is formatted with appropriate headers and indentation for readability.
     * 
     * @param ordenador The computer object containing all the components to be displayed
     */
    public void mostrarOrdenador(Ordenador ordenador) {

        StringBuilder sb = new StringBuilder();
        sb.append("DETALLES DEL ORDENADOR\n");
        sb.append("======================\n\n");
        sb.append(String.format("Nombre: %s\n", ordenador.getNombre()));
        sb.append(String.format("Precio: %d€\n\n", ordenador.getPrecio()));

        if (ordenador.getProcesador() != null) {
            sb.append("PROCESADOR:\n");
            ordenador.getProcesador().forEach(p -> {
                sb.append(String.format("  Marca: %s, Modelo: %s, Hz: %d\n", 
                    p.getMarca(), p.getModelo(), p.getHz()));
            });
            sb.append("\n");
        }

        if (ordenador.getPlacaBase() != null) {
            sb.append("PLACA BASE:\n");
            ordenador.getPlacaBase().forEach(p -> {
                sb.append(String.format("  Marca: %s, Modelo: %s, Slots RAM: %d\n", 
                    p.getMarca(), p.getModelo(), p.getSlotsRam()));
            });
            sb.append("\n");
        }

        if (ordenador.getRam() != null) {
            sb.append("RAM:\n");
            ordenador.getRam().forEach(r -> {
                sb.append(String.format("  Marca: %s, Tipo: %s, Capacidad: %s, Hz: %d, CL: %d\n", 
                    r.getMarca(), r.getTipo(), r.getCapacidad(), r.getHz(), r.getCl()));
            });
            sb.append("\n");
        }

        if (ordenador.getDiscoDuro() != null) {
            sb.append("DISCO DURO:\n");
            ordenador.getDiscoDuro().forEach(d -> {
                sb.append(String.format("  Marca: %s, Tipo: %s, Capacidad: %s\n", 
                    d.getMarca(), d.getTipo(), d.getCapacidad()));
            });
            sb.append("\n");
        }

        if (ordenador.getFuenteAlimentacion() != null) {
            sb.append("FUENTE DE ALIMENTACIÓN:\n");
            ordenador.getFuenteAlimentacion().forEach(f -> {
                sb.append(String.format("  Marca: %s, Modelo: %s, Potencia: %dW\n", 
                    f.getMarca(), f.getModelo(), f.getPotencia()));
            });
            sb.append("\n");
        }

        if (ordenador.getTarjetaGrafica() != null) {
            sb.append("TARJETA GRÁFICA:\n");
            ordenador.getTarjetaGrafica().forEach(t -> {
                sb.append(String.format("  Marca: %s, Modelo: %s, CUDA Cores: %d\n", 
                    t.getMarca(), t.getModelo(), t.getCudas()));
            });
            sb.append("\n");
        }

        if (ordenador.getPerifericos() != null) {
            sb.append("PERIFÉRICOS:\n");
            ordenador.getPerifericos().forEach(p -> {
                if (p.getTeclado() != null) {
                    sb.append(String.format("  Teclado: %s %s (%s)\n", 
                        p.getTeclado().getMarca(), p.getTeclado().getNombre(), p.getTeclado().getTipo()));
                }
                if (p.getRaton() != null) {
                    sb.append(String.format("  Ratón: %s %s (%s)\n", 
                        p.getRaton().getMarca(), p.getRaton().getNombre(), p.getRaton().getTipo()));
                }
                if (p.getMonitor() != null) {
                    sb.append(String.format("  Monitor: %s %s (%s)\n", 
                        p.getMonitor().getMarca(), p.getMonitor().getNombre(), p.getMonitor().getTipo()));
                }
            });
        }

        textArea.setText(sb.toString());
    }
}
