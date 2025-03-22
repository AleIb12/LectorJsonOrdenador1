package es.upgrade;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class OrdenadorUI extends JFrame {
    private static final Color BACKGROUND_COLOR = new Color(240, 240, 245);
    private static final Color TITLE_COLOR = new Color(30, 30, 70);
    private static final Color HEADER_COLOR = new Color(60, 100, 170);
    private static final Color PANEL_COLOR = new Color(250, 250, 255);
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 20);
    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 14);
    private static final Font CONTENT_FONT = new Font("Arial", Font.PLAIN, 12);

    private JPanel mainPanel;
    private JTabbedPane tabbedPane;

    public OrdenadorUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error al establecer el Look & Feel: " + e.getMessage());
        }

        setTitle("Detalles del Ordenador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        
        // Crear panel principal
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Crear pestañas para organizar la información
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(HEADER_FONT);
        
        // Añadir el panel principal a la ventana
        add(mainPanel);
        
        // Centrar la ventana
        setLocationRelativeTo(null);
        
        // Añadir listener para guardar tamaño y posición
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Displays the detailed information of a computer in a modern tabbed interface.
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
     * Each component's information is displayed in a formatted panel with colors and icons.
     * 
     * @param ordenador The computer object containing all the components to be displayed
     */
    public void mostrarOrdenador(Ordenador ordenador) {
        // Limpiar contenido anterior
        mainPanel.removeAll();
        tabbedPane.removeAll();
        
        // Crear panel de información general
        JPanel generalPanel = createGeneralInfoPanel(ordenador);
        
        // Crear pestañas para componentes
        JPanel procesadorPanel = createComponentPanel("Procesadores", createProcessorContent(ordenador.getProcesador()));
        JPanel placaBasePanel = createComponentPanel("Placa Base", createMotherboardContent(ordenador.getPlacaBase()));
        JPanel ramPanel = createComponentPanel("Memoria RAM", createRamContent(ordenador.getRam()));
        JPanel discoPanel = createComponentPanel("Almacenamiento", createStorageContent(ordenador.getDiscoDuro()));
        JPanel fuentePanel = createComponentPanel("Fuente de Alimentación", createPSUContent(ordenador.getFuenteAlimentacion()));
        JPanel graficaPanel = createComponentPanel("Tarjeta Gráfica", createGPUContent(ordenador.getTarjetaGrafica()));
        JPanel perifericosPanel = createPeripheralsPanel(ordenador.getPerifericos());
        
        // Añadir pestañas
        tabbedPane.addTab("General", generalPanel);
        tabbedPane.addTab("Procesador", procesadorPanel);
        tabbedPane.addTab("Placa Base", placaBasePanel);
        tabbedPane.addTab("RAM", ramPanel);
        tabbedPane.addTab("Almacenamiento", discoPanel);
        tabbedPane.addTab("Fuente", fuentePanel);
        tabbedPane.addTab("Gráfica", graficaPanel);
        tabbedPane.addTab("Periféricos", perifericosPanel);
        
        // Añadir pestañas al panel principal
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        
        // Refrescar la interfaz
        revalidate();
        repaint();
    }
    
    private JPanel createGeneralInfoPanel(Ordenador ordenador) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBackground(PANEL_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Título
        JLabel titleLabel = new JLabel(ordenador.getNombre());
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TITLE_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        // Precio
        JLabel priceLabel = new JLabel("Precio: " + ordenador.getPrecio() + " €");
        priceLabel.setFont(HEADER_FONT);
        priceLabel.setForeground(HEADER_COLOR);
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Resumen de componentes
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new GridLayout(0, 2, 10, 5));
        summaryPanel.setBackground(PANEL_COLOR);
        summaryPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(HEADER_COLOR), 
            "Resumen de Componentes", 
            TitledBorder.LEFT, 
            TitledBorder.TOP, 
            HEADER_FONT, 
            HEADER_COLOR
        ));
        
        addSummaryItem(summaryPanel, "Procesadores", ordenador.getProcesador() != null ? ordenador.getProcesador().size() : 0);
        addSummaryItem(summaryPanel, "Placas Base", ordenador.getPlacaBase() != null ? ordenador.getPlacaBase().size() : 0);
        addSummaryItem(summaryPanel, "Módulos RAM", ordenador.getRam() != null ? ordenador.getRam().size() : 0);
        addSummaryItem(summaryPanel, "Discos", ordenador.getDiscoDuro() != null ? ordenador.getDiscoDuro().size() : 0);
        addSummaryItem(summaryPanel, "Fuentes de Alimentación", ordenador.getFuenteAlimentacion() != null ? ordenador.getFuenteAlimentacion().size() : 0);
        addSummaryItem(summaryPanel, "Tarjetas Gráficas", ordenador.getTarjetaGrafica() != null ? ordenador.getTarjetaGrafica().size() : 0);
        addSummaryItem(summaryPanel, "Periféricos", ordenador.getPerifericos() != null ? ordenador.getPerifericos().size() : 0);
        
        // Añadir componentes al panel
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(summaryPanel, BorderLayout.CENTER);
        panel.add(priceLabel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void addSummaryItem(JPanel panel, String label, int count) {
        JLabel nameLabel = new JLabel(label + ":");
        nameLabel.setFont(CONTENT_FONT);
        
        JLabel countLabel = new JLabel(String.valueOf(count));
        countLabel.setFont(CONTENT_FONT);
        countLabel.setForeground(HEADER_COLOR);
        
        panel.add(nameLabel);
        panel.add(countLabel);
    }
    
    private JPanel createComponentPanel(String title, JPanel contentPanel) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(PANEL_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(HEADER_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createProcessorContent(List<Procesador> procesadores) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(PANEL_COLOR);
        
        if (procesadores != null && !procesadores.isEmpty()) {
            for (Procesador p : procesadores) {
                JPanel procPanel = new JPanel();
                procPanel.setLayout(new GridLayout(0, 2, 5, 5));
                procPanel.setBackground(PANEL_COLOR);
                procPanel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(HEADER_COLOR, 1), 
                    p.getMarca() + " " + p.getModelo(), 
                    TitledBorder.LEFT, 
                    TitledBorder.TOP, 
                    HEADER_FONT, 
                    HEADER_COLOR
                ));
                procPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
                
                addPropertyField(procPanel, "Marca", p.getMarca());
                addPropertyField(procPanel, "Modelo", p.getModelo());
                addPropertyField(procPanel, "Frecuencia", p.getHz() + " MHz");
                
                panel.add(procPanel);
                panel.add(Box.createVerticalStrut(10));
            }
        } else {
            addNoComponentsMessage(panel, "No hay procesadores configurados");
        }
        
        return panel;
    }
    
    private JPanel createMotherboardContent(List<PlacaBase> placas) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(PANEL_COLOR);
        
        if (placas != null && !placas.isEmpty()) {
            for (PlacaBase p : placas) {
                JPanel mbPanel = new JPanel();
                mbPanel.setLayout(new GridLayout(0, 2, 5, 5));
                mbPanel.setBackground(PANEL_COLOR);
                mbPanel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(HEADER_COLOR, 1), 
                    p.getMarca() + " " + p.getModelo(), 
                    TitledBorder.LEFT, 
                    TitledBorder.TOP, 
                    HEADER_FONT, 
                    HEADER_COLOR
                ));
                mbPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
                
                addPropertyField(mbPanel, "Marca", p.getMarca());
                addPropertyField(mbPanel, "Modelo", p.getModelo());
                addPropertyField(mbPanel, "Slots RAM", String.valueOf(p.getSlotsRam()));
                
                panel.add(mbPanel);
                panel.add(Box.createVerticalStrut(10));
            }
        } else {
            addNoComponentsMessage(panel, "No hay placas base configuradas");
        }
        
        return panel;
    }
    
    private JPanel createRamContent(List<Ram> rams) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(PANEL_COLOR);
        
        if (rams != null && !rams.isEmpty()) {
            for (Ram r : rams) {
                JPanel ramPanel = new JPanel();
                ramPanel.setLayout(new GridLayout(0, 2, 5, 5));
                ramPanel.setBackground(PANEL_COLOR);
                ramPanel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(HEADER_COLOR, 1), 
                    r.getMarca() + " " + r.getTipo() + " " + r.getCapacidad(), 
                    TitledBorder.LEFT, 
                    TitledBorder.TOP, 
                    HEADER_FONT, 
                    HEADER_COLOR
                ));
                ramPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
                
                addPropertyField(ramPanel, "Marca", r.getMarca());
                addPropertyField(ramPanel, "Tipo", r.getTipo());
                addPropertyField(ramPanel, "Capacidad", r.getCapacidad());
                addPropertyField(ramPanel, "Frecuencia", r.getHz() + " MHz");
                addPropertyField(ramPanel, "Latencia", "CL" + r.getCl());
                
                panel.add(ramPanel);
                panel.add(Box.createVerticalStrut(10));
            }
        } else {
            addNoComponentsMessage(panel, "No hay módulos RAM configurados");
        }
        
        return panel;
    }
    
    private JPanel createStorageContent(List<DiscoDuro> discos) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(PANEL_COLOR);
        
        if (discos != null && !discos.isEmpty()) {
            for (DiscoDuro d : discos) {
                JPanel discPanel = new JPanel();
                discPanel.setLayout(new GridLayout(0, 2, 5, 5));
                discPanel.setBackground(PANEL_COLOR);
                discPanel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(HEADER_COLOR, 1), 
                    d.getMarca() + " " + d.getTipo() + " " + d.getCapacidad(), 
                    TitledBorder.LEFT, 
                    TitledBorder.TOP, 
                    HEADER_FONT, 
                    HEADER_COLOR
                ));
                discPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
                
                addPropertyField(discPanel, "Marca", d.getMarca());
                addPropertyField(discPanel, "Tipo", d.getTipo());
                addPropertyField(discPanel, "Capacidad", d.getCapacidad());
                
                panel.add(discPanel);
                panel.add(Box.createVerticalStrut(10));
            }
        } else {
            addNoComponentsMessage(panel, "No hay discos duros configurados");
        }
        
        return panel;
    }
    
    private JPanel createPSUContent(List<FuenteAlimentacion> fuentes) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(PANEL_COLOR);
        
        if (fuentes != null && !fuentes.isEmpty()) {
            for (FuenteAlimentacion f : fuentes) {
                JPanel fuentePanel = new JPanel();
                fuentePanel.setLayout(new GridLayout(0, 2, 5, 5));
                fuentePanel.setBackground(PANEL_COLOR);
                fuentePanel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(HEADER_COLOR, 1), 
                    f.getMarca() + " " + f.getModelo() + " " + f.getPotencia() + "W", 
                    TitledBorder.LEFT, 
                    TitledBorder.TOP, 
                    HEADER_FONT, 
                    HEADER_COLOR
                ));
                fuentePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
                
                addPropertyField(fuentePanel, "Marca", f.getMarca());
                addPropertyField(fuentePanel, "Modelo", f.getModelo());
                addPropertyField(fuentePanel, "Potencia", f.getPotencia() + "W");
                
                panel.add(fuentePanel);
                panel.add(Box.createVerticalStrut(10));
            }
        } else {
            addNoComponentsMessage(panel, "No hay fuentes de alimentación configuradas");
        }
        
        return panel;
    }
    
    private JPanel createGPUContent(List<TarjetaGrafica> tarjetas) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(PANEL_COLOR);
        
        if (tarjetas != null && !tarjetas.isEmpty()) {
            for (TarjetaGrafica t : tarjetas) {
                JPanel gpuPanel = new JPanel();
                gpuPanel.setLayout(new GridLayout(0, 2, 5, 5));
                gpuPanel.setBackground(PANEL_COLOR);
                gpuPanel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(HEADER_COLOR, 1), 
                    t.getMarca() + " " + t.getModelo(), 
                    TitledBorder.LEFT, 
                    TitledBorder.TOP, 
                    HEADER_FONT, 
                    HEADER_COLOR
                ));
                gpuPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
                
                addPropertyField(gpuPanel, "Marca", t.getMarca());
                addPropertyField(gpuPanel, "Modelo", t.getModelo());
                addPropertyField(gpuPanel, "CUDA Cores", String.valueOf(t.getCudas()));
                
                panel.add(gpuPanel);
                panel.add(Box.createVerticalStrut(10));
            }
        } else {
            addNoComponentsMessage(panel, "No hay tarjetas gráficas configuradas");
        }
        
        return panel;
    }
    
    private JPanel createPeripheralsPanel(List<Periferico> perifericos) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(PANEL_COLOR);
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(PANEL_COLOR);
        
        if (perifericos != null && !perifericos.isEmpty()) {
            for (Periferico p : perifericos) {
                // Teclado
                if (p.getTeclado() != null) {
                    JPanel tecladoPanel = new JPanel();
                    tecladoPanel.setLayout(new GridLayout(0, 2, 5, 5));
                    tecladoPanel.setBackground(PANEL_COLOR);
                    tecladoPanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(HEADER_COLOR, 1), 
                        "Teclado", 
                        TitledBorder.LEFT, 
                        TitledBorder.TOP, 
                        HEADER_FONT, 
                        HEADER_COLOR
                    ));
                    tecladoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
                    
                    addPropertyField(tecladoPanel, "Marca", p.getTeclado().getMarca());
                    addPropertyField(tecladoPanel, "Nombre", p.getTeclado().getNombre());
                    addPropertyField(tecladoPanel, "Tipo", p.getTeclado().getTipo());
                    
                    contentPanel.add(tecladoPanel);
                    contentPanel.add(Box.createVerticalStrut(10));
                }
                
                // Ratón
                if (p.getRaton() != null) {
                    JPanel ratonPanel = new JPanel();
                    ratonPanel.setLayout(new GridLayout(0, 2, 5, 5));
                    ratonPanel.setBackground(PANEL_COLOR);
                    ratonPanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(HEADER_COLOR, 1), 
                        "Ratón", 
                        TitledBorder.LEFT, 
                        TitledBorder.TOP, 
                        HEADER_FONT, 
                        HEADER_COLOR
                    ));
                    ratonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
                    
                    addPropertyField(ratonPanel, "Marca", p.getRaton().getMarca());
                    addPropertyField(ratonPanel, "Nombre", p.getRaton().getNombre());
                    addPropertyField(ratonPanel, "Tipo", p.getRaton().getTipo());
                    
                    contentPanel.add(ratonPanel);
                    contentPanel.add(Box.createVerticalStrut(10));
                }
                
                // Monitor
                if (p.getMonitor() != null) {
                    JPanel monitorPanel = new JPanel();
                    monitorPanel.setLayout(new GridLayout(0, 2, 5, 5));
                    monitorPanel.setBackground(PANEL_COLOR);
                    monitorPanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(HEADER_COLOR, 1), 
                        "Monitor", 
                        TitledBorder.LEFT, 
                        TitledBorder.TOP, 
                        HEADER_FONT, 
                        HEADER_COLOR
                    ));
                    monitorPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
                    
                    addPropertyField(monitorPanel, "Marca", p.getMonitor().getMarca());
                    addPropertyField(monitorPanel, "Nombre", p.getMonitor().getNombre());
                    addPropertyField(monitorPanel, "Tipo", p.getMonitor().getTipo());
                    
                    contentPanel.add(monitorPanel);
                    contentPanel.add(Box.createVerticalStrut(10));
                }
            }
        } else {
            addNoComponentsMessage(contentPanel, "No hay periféricos configurados");
        }
        
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void addPropertyField(JPanel panel, String label, String value) {
        JLabel nameLabel = new JLabel(label + ":");
        nameLabel.setFont(CONTENT_FONT);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(CONTENT_FONT);
        valueLabel.setForeground(HEADER_COLOR);
        
        panel.add(nameLabel);
        panel.add(valueLabel);
    }
    
    private void addNoComponentsMessage(JPanel panel, String message) {
        JLabel label = new JLabel(message);
        label.setFont(CONTENT_FONT);
        label.setForeground(Color.GRAY);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);
    }
}
