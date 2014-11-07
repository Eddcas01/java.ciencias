import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
public class Ventana extends JFrame implements ActionListener{
    private JFrame frame = new JFrame("Inicio");
    private JPanel panel = new JPanel();
    
    private static Agenda miAgenda;
    private static int vistaActual;
    private ArrayList<JTextField> camposDeEntrada;
    private ArrayList<JLabel> etiquetas;
    private ArrayList<JButton> botones1;
    private ArrayList<JButton> botones2;
    private ArrayList<JButton> botones3;

    
        public Ventana() {
        camposDeEntrada = new  ArrayList<>();
        etiquetas = new ArrayList<>();
        botones1 = new ArrayList<>();
        botones2 = new ArrayList<>();
        botones3 = new ArrayList<>(); 
        vistaActual = 0;
        panel = new JPanel();
        frame = new JFrame();
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        setContentPane(pane);
        
    }

   
       
    public void iniciar(){
//        miAgenda.cargarContactos();
        miAgenda.importarContactosDeUnArchivo("/tmp/miAgenda.json");
        System.out.println(miAgenda);
        vistaInicial();
    }
 
        
        
    public void vistaInicial(){
        frame.setTitle("Agenda");
        ArrayList<Contacto> contactos = miAgenda.getContactos();
        
        panel.removeAll();
        panel.repaint();
        
        etiquetas = new ArrayList<>();
        botones1 = new ArrayList<>();
        botones2 = new ArrayList<>();
        botones3 = new ArrayList<>();

        
        int i = 0;
        for (Contacto contacto : contactos) {
            i++;
            JLabel fulano = new JLabel(contacto.getNombre() + " "+ contacto.getApellido());
            fulano.setBounds(10,10 + 50*i,300,50);
            etiquetas.add(fulano);
            panel.add(fulano);
            
            JButton boton = new JButton("V");
            boton.setBounds(320, 10 + 50*i, 50, 50);
            botones1.add(boton);
            panel.add(boton);
            boton.addActionListener(this);
            
            boton = new JButton("E");
            boton.setBounds(370, 10 + 50*i, 50, 50);
            botones2.add(boton);
            panel.add(boton);
            boton.addActionListener(this);

            boton = new JButton("B");
            boton.setBounds(410, 10 + 50*i, 50, 50);
            botones3.add(boton);
            panel.add(boton);
            boton.addActionListener(this);

            
            frame.add(panel);
            
        }        
    }
        
        
    
  public static void main(String[] a) {
      
    miAgenda = new Agenda();
  
    Ventana ventana = new Ventana();
    
    ventana.iniciar();
    ventana.frame.setSize(480, 640);
    ventana.setBounds(0,0,480,640);

    ventana.frame.setVisible(true);
  }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (botones1.contains(e.getSource())) {
            System.out.println("Un botón 'VER' fue presionado");
            return;
        }
        if (botones2.contains(e.getSource())) {
            System.out.println("Un botón 'EDITAR' fue presionado");
            return;
        }
        if (botones3.contains(e.getSource())) {
            switch(vistaActual){
                case 0:
                    int i = botones3.indexOf(e.getSource());
                    System.out.println("borrar al contacto" + i);
                    miAgenda.eliminarContacto(miAgenda.getContactos().get(i));
                    vistaInicial();
                    break;
                    
            }
            return;
        }
        
    }
}