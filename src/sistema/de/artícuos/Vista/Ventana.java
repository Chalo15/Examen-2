/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.artícuos.Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import sistema.de.articulos.Control.ClienteSocket;

/**
 *
 * @author Gonzalo
 */
public class Ventana extends JFrame {

    private Thread hilito;
    private ClienteSocket cli;
    private String nombre, descripcion, categoria;
    private int precio, cantidad;
    int banderaOpcion;
    private Articulo articulo;
    DefaultTableModel Tarticulo = new DefaultTableModel();
    private String[] nameColums = {"Categoria", "Articulo", "Cantidad", "Precio", "Descripcion"};
    private javax.swing.JTextField CantidadTxt;
    private javax.swing.JComboBox<String> CategoriaCombo;
    private javax.swing.JComboBox<String> CategoriaCombo2;
    private javax.swing.JLabel Categorialb;
    private javax.swing.JTextField DescripcionTxt;
    private javax.swing.JLabel Descripcionlb;
    private javax.swing.JButton Eliminarbtn;
    private javax.swing.JButton FiltarBtn;
    private javax.swing.JButton GuardarBtn;
    private javax.swing.JTextField NombreTxt;
    private javax.swing.JLabel Nombrelb;
    private javax.swing.JTextField PrecioTxt;
    private javax.swing.JLabel Preciolb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    String artic = "";
    //private GestorCliente gestor=new GestorCliente(this);

    public Ventana() {
        super("Examen 2");
        cli = new ClienteSocket(this);
        conexion();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        iniciarComponentes();
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void conexion() {
        hilito = new Thread(cli);
        if (hilito != null) {
            hilito.start();
        }
    }

    public void actualizarTabla(String v) {
 //String[] obj = v.split(";");
      //  for (String prod : obj) {
          //  String[] produc = prod.split("_");
//            Object[] vec = new Object[5];
//            vec[0] = produc[0];
//            vec[1] = produc[1];
//            vec[2] = produc[2];
//            vec[3] = produc[3];
//            vec[5] = produc[5];
//
//            Tarticulo.addRow(vec);
//        }
        String[] obj = v.split(";");
        String mat[][] = new String[obj.length][5];
        int cont=0;
        for (String prod :  obj) {
            String[] produc = prod.split("_");
                    mat[cont][0]= produc[0];
                    mat[cont][1] = produc[1];
                    mat[cont][2] = produc[2];
                    mat[cont][3] = produc[3];
                    mat[cont][4] = produc[4];
                    cont++;
        }
        
                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                                mat, nameColums
                ));        
                 
                
            

        //Tarticulo.addColumn(nameColums);
        //jTable1.setModel(Tarticulo);
    }

    public static void main(String[] args) {
        Ventana ventana = new Ventana();
        ventana.setVisible(true);
    }

    public void iniciarComponentes() {
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        NombreTxt = new javax.swing.JTextField();
        DescripcionTxt = new javax.swing.JTextField();
        Categorialb = new javax.swing.JLabel();
        GuardarBtn = new javax.swing.JButton();
        Nombrelb = new javax.swing.JLabel();
        Descripcionlb = new javax.swing.JLabel();
        CategoriaCombo = new javax.swing.JComboBox<>();
        Preciolb = new javax.swing.JLabel();
        PrecioTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        CantidadTxt = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Eliminarbtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        FiltarBtn = new javax.swing.JButton();
        CategoriaCombo2 = new javax.swing.JComboBox<>();

        jSplitPane1.setDividerLocation(220);
        Categorialb.setText("Categoría");
        GuardarBtn.setText("Guardar");
        Nombrelb.setText("Nombre");
        Descripcionlb.setText("Descripcion");
        CategoriaCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Lacteos", "Abarrotes", "Libros", "Herramientas", "Juguetes", "Oficina"}));
        Preciolb.setText("Precio");
        jLabel1.setText("Cantidad");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(Nombrelb)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(NombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(Descripcionlb)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(DescripcionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(GuardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(Categorialb, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(CategoriaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(Preciolb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(PrecioTxt)
                                                        .addComponent(CantidadTxt))))
                                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(NombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Nombrelb))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(DescripcionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Descripcionlb))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Preciolb)
                                        .addComponent(PrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(CantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(CategoriaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Categorialb))
                                .addGap(18, 18, 18)
                                .addComponent(GuardarBtn)
                                .addGap(58, 58, 58))
        );

        jSplitPane1.setLeftComponent(jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"Lacteos", "Yogurt", 10, 2500, "Light"},
                    {"Abarrotes", "Aceite Mazola", "Para cocinar", 20, 2700},
                    {"Libros", "En las motañas de la locura", 7, 5000, "Autor HP Lovecraft"},
                    {"Herramientas", "Taladro", 5, 15000, "Herramienta de hogar"},
                    {"Juguetes", "Circuito de carros", 9, 7000, "Para juegos "},
                    {"Jueguetes", "Castillo de princesas", 5, 7000, "Para juegos"},
                    {"Oficina", "Silla de oficina", 8, 20000, "Asiento para oficina"},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null}
                },
                new String[]{
                    "Categoría", "Artículo", "Cantidad", "Precio", "Descripcion"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        Eliminarbtn.setText("Eliminar");
        jLabel2.setText("Categoría");
        FiltarBtn.setText("Filtrar");
        CategoriaCombo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Lacteos", "Abarrotes", "Libros", "Herramientas", "Juguetes", "Oficina"}));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(25, 25, 25)
                                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(CategoriaCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(FiltarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(191, 191, 191)
                                                                .addComponent(Eliminarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(FiltarBtn)
                                        .addComponent(CategoriaCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Eliminarbtn)
                                .addContainerGap(28, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSplitPane1)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSplitPane1)
                                .addContainerGap())
        );

        pack();

        agregarAccion();
    }

    public void agregarAccion() {
        GuardarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (NombreTxt.getText().length() == 0 || DescripcionTxt.getText().length() == 0 || PrecioTxt.getText().length() == 0 || CantidadTxt.getText().length() == 0 || CategoriaCombo.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "Has dejado uno o mas campos sin llenar");
                } else {
                    try {
                        nombre = NombreTxt.getText();
                        descripcion = DescripcionTxt.getText();
                        precio = Integer.parseInt(PrecioTxt.getText());
                        cantidad = Integer.parseInt(CantidadTxt.getText());
                        categoria = String.valueOf(CategoriaCombo.getSelectedItem());
                        banderaOpcion = 1;
                        artic = banderaOpcion + "_" + nombre + "_" + descripcion + "_" + precio + "_" + cantidad + "_" + categoria;
                        //articulo=new Articulo(nombre,descripcion,categoria,precio,cantidad,banderaOpcion);
                        System.out.println("Enviando datos al servidor: ");

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Favor de ingresar un valor numérico en las casillas de precio y cantidad", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    System.out.print(artic);
                    cli.enviarAlServidor(artic);

                }
            }
        });

        FiltarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
//               
                    categoria = String.valueOf(CategoriaCombo2.getSelectedItem());
                    banderaOpcion = 2;
                    artic = banderaOpcion + "_" + categoria;

                    
                    System.out.println("Enviando datos al servidor: ");
                    cli.enviarAlServidor(artic);
                
            }
        });

        Eliminarbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
//                if (jTable1.getSelectedRow() == -1 || articulo.getLista() == null) {
//                    JOptionPane.showMessageDialog(null, "Ingresar un articulo o marcar uno de la lista que aparece");
//                } else {
                    categoria = String.valueOf(CategoriaCombo2.getSelectedItem());
                    banderaOpcion = 3;
                    artic = banderaOpcion + "_" + categoria;

                    //articulo=new Articulo("","",categoria,0,0,banderaOpcion);
                    System.out.println("Enviando datos al servidor: ");
                    cli.enviarAlServidor(artic);

                //}
            }
        });

    }

      
}
