package howmanycals;

import static howmanycals.utils.FormatUtils.formatDoubleValueForTableVisualisation;

import howmanycals.db.dao.HowManyCalsDAO;
import howmanycals.domain.Category;
import howmanycals.domain.NutritionalIngredient;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainWindow extends JFrame {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MainWindow.class.getSimpleName());
    
    private HowManyCalsDAO dao;
    private List<Category> categories;
    private List<NutritionalIngredient> ingredients;

    private void initDatabase() {
        this.dao = new HowManyCalsDAO();
        this.dao.init();
    }

    public MainWindow() {
        this.initDatabase();
        this.initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addNewIngredientDialog = new javax.swing.JDialog();
        newIngredientNameLabel = new javax.swing.JLabel();
        newIngredientNameText = new javax.swing.JTextField();
        newIngredientGramsLabel = new javax.swing.JLabel();
        newIngredientGramsField = new javax.swing.JTextField();
        newIngredientCaloriesLabel = new javax.swing.JLabel();
        newIngredientCaloriesField = new javax.swing.JTextField();
        newIngredientFatLabel = new javax.swing.JLabel();
        newIngredientFatField = new javax.swing.JTextField();
        newIngredientSugarLabel = new javax.swing.JLabel();
        newIngredientSugarField = new javax.swing.JTextField();
        newIngredientCarbohydratesLabel = new javax.swing.JLabel();
        newIngredientCarbohydratesField = new javax.swing.JTextField();
        newIngredientProteinLabel = new javax.swing.JLabel();
        newIngredientProteinField = new javax.swing.JTextField();
        newIngredientCholesterolLabel = new javax.swing.JLabel();
        newIngredientCholesterolField = new javax.swing.JTextField();
        newIngredientSodiumLabel = new javax.swing.JLabel();
        newIngredientSodiumField = new javax.swing.JTextField();
        newIngredientCategoryLabel = new javax.swing.JLabel();
        newIngredientCategoryList = new javax.swing.JComboBox<>();
        addNewIngredientButton = new javax.swing.JButton();
        clearNewIngredientButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        newIngredientNotesField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        viewIngredientDialog = new javax.swing.JDialog();
        okViewIngredientButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        viewIngredientTable = new javax.swing.JTable();
        searchViewIngredientLabel = new javax.swing.JLabel();
        searchViewIngredientTextField = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        valuesSummaryPanel = new javax.swing.JPanel();
        summaryStaticCaloriesLabel = new javax.swing.JLabel();
        summaryStaticProteinLabel = new javax.swing.JLabel();
        summaryStaticCarbsLabel = new javax.swing.JLabel();
        summaryStaticSugarLabel = new javax.swing.JLabel();
        summaryStaticFatLabel = new javax.swing.JLabel();
        summaryStaticCholesterolLabel = new javax.swing.JLabel();
        summaryCaloriesLabel = new javax.swing.JLabel();
        summaryProteinLabel = new javax.swing.JLabel();
        summaryCarbsLabel = new javax.swing.JLabel();
        summarySugarLabel = new javax.swing.JLabel();
        summaryFatLabel = new javax.swing.JLabel();
        summaryCholesterolLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenuItem = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        ingredientsMenuItem = new javax.swing.JMenu();
        addIngredientMenuItem = new javax.swing.JMenuItem();
        viewIngredientMenuItem = new javax.swing.JMenuItem();

        addNewIngredientDialog.setTitle("Add Ingredient");
        addNewIngredientDialog.setMinimumSize(new java.awt.Dimension(430, 540));
        addNewIngredientDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        addNewIngredientDialog.setResizable(false);
        addNewIngredientDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                addNewIngredientDialogWindowOpened(evt);
            }
        });

        newIngredientNameLabel.setText("Name:");

        newIngredientGramsLabel.setText("Grams (g):");

        newIngredientCaloriesLabel.setText("Calories:");

        newIngredientFatLabel.setText("Fat:");

        newIngredientSugarLabel.setText("Sugar:");

        newIngredientCarbohydratesLabel.setText("Carbohydrates:");

        newIngredientProteinLabel.setText("Protein:");

        newIngredientCholesterolLabel.setText("Cholesterol:");

        newIngredientSodiumLabel.setText("Sodium:");

        newIngredientCategoryLabel.setText("Category:");

        newIngredientCategoryList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newIngredientCategoryListActionPerformed(evt);
            }
        });

        addNewIngredientButton.setMnemonic('A');
        addNewIngredientButton.setText("Add");
        addNewIngredientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewIngredientButtonActionPerformed(evt);
            }
        });

        clearNewIngredientButton.setMnemonic('C');
        clearNewIngredientButton.setText("Clear");
        clearNewIngredientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearNewIngredientButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(248, 3, 3));
        jLabel1.setText("(*)");

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(248, 11, 11));
        jLabel2.setText("(*)");

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(251, 4, 4));
        jLabel3.setText("(*)");

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(250, 5, 5));
        jLabel4.setText("(*)");

        jLabel6.setText("Notes:");

        javax.swing.GroupLayout addNewIngredientDialogLayout = new javax.swing.GroupLayout(addNewIngredientDialog.getContentPane());
        addNewIngredientDialog.getContentPane().setLayout(addNewIngredientDialogLayout);
        addNewIngredientDialogLayout.setHorizontalGroup(
            addNewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addNewIngredientDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addNewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addNewIngredientDialogLayout.createSequentialGroup()
                        .addComponent(newIngredientNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(addNewIngredientDialogLayout.createSequentialGroup()
                        .addComponent(newIngredientGramsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(addNewIngredientDialogLayout.createSequentialGroup()
                        .addComponent(newIngredientCaloriesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(newIngredientFatLabel)
                    .addGroup(addNewIngredientDialogLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(newIngredientSugarLabel))
                    .addComponent(newIngredientCarbohydratesLabel)
                    .addComponent(newIngredientProteinLabel)
                    .addComponent(newIngredientSodiumLabel)
                    .addGroup(addNewIngredientDialogLayout.createSequentialGroup()
                        .addComponent(newIngredientCategoryLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addComponent(newIngredientCholesterolLabel)
                    .addGroup(addNewIngredientDialogLayout.createSequentialGroup()
                        .addComponent(addNewIngredientButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearNewIngredientButton))
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addGroup(addNewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newIngredientNotesField)
                    .addComponent(newIngredientFatField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(newIngredientCaloriesField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(newIngredientGramsField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(newIngredientNameText, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(newIngredientSugarField)
                    .addComponent(newIngredientCarbohydratesField)
                    .addComponent(newIngredientProteinField)
                    .addComponent(newIngredientCholesterolField)
                    .addComponent(newIngredientSodiumField)
                    .addComponent(newIngredientCategoryList, 0, 283, Short.MAX_VALUE))
                .addContainerGap())
        );
        addNewIngredientDialogLayout.setVerticalGroup(
            addNewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addNewIngredientDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addNewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientNameLabel)
                    .addComponent(newIngredientNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientGramsLabel)
                    .addComponent(newIngredientGramsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientCaloriesLabel)
                    .addComponent(newIngredientCaloriesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientFatLabel)
                    .addComponent(newIngredientFatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientSugarLabel)
                    .addComponent(newIngredientSugarField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addNewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientCarbohydratesLabel)
                    .addComponent(newIngredientCarbohydratesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientProteinLabel)
                    .addComponent(newIngredientProteinField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientCholesterolLabel)
                    .addComponent(newIngredientCholesterolField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientSodiumLabel)
                    .addComponent(newIngredientSodiumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addNewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(newIngredientCategoryLabel)
                        .addComponent(jLabel4))
                    .addComponent(newIngredientCategoryList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addNewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newIngredientNotesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(addNewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addNewIngredientButton)
                    .addComponent(clearNewIngredientButton))
                .addContainerGap())
        );

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        viewIngredientDialog.setTitle("Ingredients");
        viewIngredientDialog.setMaximumSize(new java.awt.Dimension(1200, 750));
        viewIngredientDialog.setMinimumSize(new java.awt.Dimension(1200, 750));
        viewIngredientDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        viewIngredientDialog.setResizable(false);
        viewIngredientDialog.setSize(new java.awt.Dimension(1200, 750));
        viewIngredientDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                viewIngredientDialogWindowOpened(evt);
            }
        });
        viewIngredientDialog.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                viewIngredientDialogKeyPressed(evt);
            }
        });

        okViewIngredientButton.setMnemonic('O');
        okViewIngredientButton.setText("OK");
        okViewIngredientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okViewIngredientButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(14, 35, 237));
        jLabel5.setText("Current Ingredients");

        viewIngredientTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        viewIngredientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Grams", "Calories", "Fat", "Sugar", "Carbohydrates", "Protein", "Cholesterol", "Sodium", "Category", "Notes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        viewIngredientTable.setNextFocusableComponent(okViewIngredientButton);
        viewIngredientTable.getTableHeader().setReorderingAllowed(false);
        viewIngredientTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewIngredientTableMouseClicked(evt);
            }
        });
        viewIngredientTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                viewIngredientTableKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(viewIngredientTable);
        if (viewIngredientTable.getColumnModel().getColumnCount() > 0) {
            viewIngredientTable.getColumnModel().getColumn(0).setMinWidth(30);
            viewIngredientTable.getColumnModel().getColumn(0).setMaxWidth(30);
            viewIngredientTable.getColumnModel().getColumn(1).setResizable(false);
            viewIngredientTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            viewIngredientTable.getColumnModel().getColumn(2).setMinWidth(50);
            viewIngredientTable.getColumnModel().getColumn(2).setMaxWidth(50);
            viewIngredientTable.getColumnModel().getColumn(3).setMinWidth(70);
            viewIngredientTable.getColumnModel().getColumn(3).setMaxWidth(70);
            viewIngredientTable.getColumnModel().getColumn(4).setMinWidth(50);
            viewIngredientTable.getColumnModel().getColumn(4).setMaxWidth(50);
            viewIngredientTable.getColumnModel().getColumn(5).setMinWidth(50);
            viewIngredientTable.getColumnModel().getColumn(5).setMaxWidth(50);
            viewIngredientTable.getColumnModel().getColumn(6).setMinWidth(102);
            viewIngredientTable.getColumnModel().getColumn(6).setMaxWidth(102);
            viewIngredientTable.getColumnModel().getColumn(7).setMinWidth(80);
            viewIngredientTable.getColumnModel().getColumn(7).setMaxWidth(80);
            viewIngredientTable.getColumnModel().getColumn(8).setMinWidth(90);
            viewIngredientTable.getColumnModel().getColumn(8).setMaxWidth(90);
            viewIngredientTable.getColumnModel().getColumn(9).setMinWidth(70);
            viewIngredientTable.getColumnModel().getColumn(9).setMaxWidth(70);
        }

        searchViewIngredientLabel.setDisplayedMnemonic('S');
        searchViewIngredientLabel.setLabelFor(searchViewIngredientTextField);
        searchViewIngredientLabel.setText("Search:");

        searchViewIngredientTextField.setNextFocusableComponent(viewIngredientTable);
        searchViewIngredientTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchViewIngredientTextFieldActionPerformed(evt);
            }
        });
        searchViewIngredientTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchViewIngredientTextFieldKeyReleased(evt);
            }
        });

        summaryStaticCaloriesLabel.setText("Calories:");

        summaryStaticProteinLabel.setText("Protein:");

        summaryStaticCarbsLabel.setText("Carbs:");

        summaryStaticSugarLabel.setText("Sugar:");

        summaryStaticFatLabel.setText("Fat:");

        summaryStaticCholesterolLabel.setText("Cholesterol:");

        javax.swing.GroupLayout valuesSummaryPanelLayout = new javax.swing.GroupLayout(valuesSummaryPanel);
        valuesSummaryPanel.setLayout(valuesSummaryPanelLayout);
        valuesSummaryPanelLayout.setHorizontalGroup(
            valuesSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(valuesSummaryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(valuesSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(summaryStaticCaloriesLabel)
                    .addComponent(summaryStaticProteinLabel)
                    .addComponent(summaryStaticCarbsLabel)
                    .addComponent(summaryStaticSugarLabel)
                    .addComponent(summaryStaticFatLabel)
                    .addComponent(summaryStaticCholesterolLabel))
                .addGap(45, 45, 45)
                .addGroup(valuesSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(summaryCaloriesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(summaryProteinLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(summaryCarbsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(summarySugarLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(summaryFatLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(summaryCholesterolLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                .addContainerGap(345, Short.MAX_VALUE))
        );
        valuesSummaryPanelLayout.setVerticalGroup(
            valuesSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(valuesSummaryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(valuesSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(summaryStaticCaloriesLabel)
                    .addComponent(summaryCaloriesLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(valuesSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(summaryStaticProteinLabel)
                    .addComponent(summaryProteinLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(valuesSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(summaryStaticCarbsLabel)
                    .addComponent(summaryCarbsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(valuesSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(summaryStaticSugarLabel)
                    .addComponent(summarySugarLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(valuesSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(summaryStaticFatLabel)
                    .addComponent(summaryFatLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(valuesSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(summaryStaticCholesterolLabel)
                    .addComponent(summaryCholesterolLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Summary", valuesSummaryPanel);

        javax.swing.GroupLayout viewIngredientDialogLayout = new javax.swing.GroupLayout(viewIngredientDialog.getContentPane());
        viewIngredientDialog.getContentPane().setLayout(viewIngredientDialogLayout);
        viewIngredientDialogLayout.setHorizontalGroup(
            viewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewIngredientDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1176, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewIngredientDialogLayout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(okViewIngredientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewIngredientDialogLayout.createSequentialGroup()
                        .addGroup(viewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(viewIngredientDialogLayout.createSequentialGroup()
                                .addComponent(searchViewIngredientLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchViewIngredientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        viewIngredientDialogLayout.setVerticalGroup(
            viewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewIngredientDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchViewIngredientLabel)
                    .addComponent(searchViewIngredientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(viewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewIngredientDialogLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(okViewIngredientButton)
                        .addContainerGap())
                    .addGroup(viewIngredientDialogLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(40, Short.MAX_VALUE))))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HowManyCals v0.1");
        setMaximumSize(new java.awt.Dimension(900, 625));
        setResizable(false);

        fileMenuItem.setMnemonic('F');
        fileMenuItem.setText("File");

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenuItem.add(exitMenuItem);

        menuBar.add(fileMenuItem);

        ingredientsMenuItem.setMnemonic('I');
        ingredientsMenuItem.setText("Ingredients");

        addIngredientMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        addIngredientMenuItem.setMnemonic('I');
        addIngredientMenuItem.setText("Add Ingredient");
        addIngredientMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addIngredientMenuItemActionPerformed(evt);
            }
        });
        ingredientsMenuItem.add(addIngredientMenuItem);

        viewIngredientMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        viewIngredientMenuItem.setMnemonic('V');
        viewIngredientMenuItem.setText("View Ingredients");
        viewIngredientMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewIngredientMenuItemActionPerformed(evt);
            }
        });
        ingredientsMenuItem.add(viewIngredientMenuItem);

        menuBar.add(ingredientsMenuItem);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.out.println("Bye ... ");
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void addIngredientMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addIngredientMenuItemActionPerformed
        System.out.println("Add Ingredient shortcut hit ... ");
        
        addNewIngredientDialog.setVisible(true);
    }//GEN-LAST:event_addIngredientMenuItemActionPerformed

    private void addNewIngredientDialogWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_addNewIngredientDialogWindowOpened
        if (this.categories != null) {
            this.categories.clear();
        }
        this.fillUpCategories();
    }//GEN-LAST:event_addNewIngredientDialogWindowOpened

    private void addNewIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewIngredientButtonActionPerformed
        final String name = this.newIngredientNameText.getText();
        final String grams = this.newIngredientGramsField.getText();
        final String calories = this.newIngredientCaloriesField.getText();
        final String fat = this.newIngredientFatField.getText();
        final String sugar = this.newIngredientSugarField.getText();
        final String carbs = this.newIngredientCarbohydratesField.getText();
        final String protein = this.newIngredientProteinField.getText();
        final String cholesterol = this.newIngredientCholesterolField.getText();
        final String sodium = this.newIngredientSodiumField.getText();
        final int selectedIndex = this.newIngredientCategoryList.getSelectedIndex();
        final Category selectedCateory = this.categories.get(selectedIndex);
        final String notes = this.newIngredientNotesField.getText();
        
        try {
            final NutritionalIngredient.FormBuilder builder = new NutritionalIngredient.FormBuilder(name, grams);
            
            final NutritionalIngredient newIngredient =
                builder
                    .calories(calories)
                    .fat(fat)
                    .sugar(sugar)
                    .carbohydrates(carbs)
                    .protein(protein)
                    .cholesterol(cholesterol)
                    .sodium(sodium)
                    .category(selectedCateory.getName())
                    .notes(notes)
                    .build()
                    ;
            
            this.dao.createNutritionIngredient(newIngredient).ifPresent(dbIngredient -> {
                this.showInfoMessage(String.format("'%s' added correctly.", dbIngredient.getName()), "Ingredient added to database.");
            });
        } catch (final NumberFormatException ex) {
            ex.printStackTrace();
            this.showError("Missing or invalid field format", ex);
        } catch (final SQLException ex) {
            ex.printStackTrace();
            this.showError("Error creating ingredient", ex);
        }
        
    }//GEN-LAST:event_addNewIngredientButtonActionPerformed

    private void showError(final String message, final Throwable cause) {
        JOptionPane.showMessageDialog(this, cause.getMessage(), message, JOptionPane.ERROR_MESSAGE);
    }
    
    private void showInfoMessage(final String message, final String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void clearNewIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearNewIngredientButtonActionPerformed
        this.newIngredientNameText.setText("");
        this.newIngredientGramsField.setText("");
        this.newIngredientCaloriesField.setText("");
        this.newIngredientFatField.setText("");
        this.newIngredientSugarField.setText("");
        this.newIngredientCarbohydratesField.setText("");
        this.newIngredientProteinField.setText("");
        this.newIngredientCholesterolField.setText("");
        this.newIngredientSodiumField.setText("");
        this.newIngredientNotesField.setText("");
    }//GEN-LAST:event_clearNewIngredientButtonActionPerformed

    private void newIngredientCategoryListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newIngredientCategoryListActionPerformed
        final int selectedIndex = this.newIngredientCategoryList.getSelectedIndex();
        final Category selectedCateory = this.categories.get(selectedIndex);
        System.out.println(selectedCateory);
        
    }//GEN-LAST:event_newIngredientCategoryListActionPerformed

    private void viewIngredientMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewIngredientMenuItemActionPerformed
        this.resetViewIngredientTable();
        this.fillViewIngredientTable();
        
        this.viewIngredientDialog.setVisible(true);
    }//GEN-LAST:event_viewIngredientMenuItemActionPerformed

    private void okViewIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okViewIngredientButtonActionPerformed
        this.viewIngredientDialog.setVisible(false);
    }//GEN-LAST:event_okViewIngredientButtonActionPerformed

    private void searchViewIngredientTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchViewIngredientTextFieldActionPerformed
        System.out.println("Enter?...");
    }//GEN-LAST:event_searchViewIngredientTextFieldActionPerformed

    private void viewIngredientDialogWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_viewIngredientDialogWindowOpened
        this.resetViewIngredientTable();
        this.fillViewIngredientTable();
    }//GEN-LAST:event_viewIngredientDialogWindowOpened

    private void viewIngredientDialogKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_viewIngredientDialogKeyPressed
        System.out.println("Key closed ... ");
    }//GEN-LAST:event_viewIngredientDialogKeyPressed

    private void rebuildViewTableFromSearchText(final String searchText) {
        if (this.ingredients == null || this.ingredients.isEmpty()) {
            LOGGER.debug("ingredients empty ... nothing to do here ... ");
            return;
        }
        
        ((DefaultTableModel) this.viewIngredientTable.getModel()).setRowCount(0);
        
        final List<NutritionalIngredient> ingredientsContainingSearchText = 
            this.ingredients.stream().filter(ingredient -> ingredient
                    .getName()
                    .toLowerCase()
                    .contains(searchText))
                .collect(Collectors.toList());
        
        ingredientsContainingSearchText.forEach(System.out::println);
        
        this.buildViewIngredientsTableWith(ingredientsContainingSearchText);
    }
    
    private void searchViewIngredientTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchViewIngredientTextFieldKeyReleased
        final String searchText = this.searchViewIngredientTextField.getText();
        if (searchText.isEmpty()) {
            if (!this.ingredients.isEmpty()) {
                this.resetSummaryLabels();
                this.buildViewIngredientsTableWith(this.ingredients);
            }
            return;
        }
        
        this.rebuildViewTableFromSearchText(searchText.toLowerCase());
    }//GEN-LAST:event_searchViewIngredientTextFieldKeyReleased

    private void resetSummaryLabels() {
        this.summaryCaloriesLabel.setText("");
        this.summaryProteinLabel.setText("");
        this.summarySugarLabel.setText("");
        this.summaryCarbsLabel.setText("");
        this.summaryFatLabel.setText("");
        this.summaryCholesterolLabel.setText("");
    }
    
    private void calculateSummaryFromSelectedRows(final int[] selectedRowIndexes) {
        double calories = 0d;
        double protein = 0d;
        double sugar = 0d;
        double carbs = 0d;
        double fat = 0d;
        double cholesterol = 0d;
        
        final DefaultTableModel viewIngredientTableModel = (DefaultTableModel) viewIngredientTable.getModel();
        
        
        for (final int idx : selectedRowIndexes) {
            final int dbIdx = (Integer)viewIngredientTableModel.getValueAt(idx, 0);
            
            final Optional<NutritionalIngredient> dbIngredient = 
                    this.ingredients.stream().filter(ingredient -> ingredient.getId() == dbIdx).findAny();
            
            if (dbIngredient.isPresent()) {
                final NutritionalIngredient ingredient = dbIngredient.get();
                calories += (ingredient.getCalories() == -1d) ? 0d : ingredient.getCalories();
                protein += (ingredient.getProtein() == -1d) ? 0d : ingredient.getProtein();
                sugar += (ingredient.getSugar() == -1d) ? 0d : ingredient.getSugar();
                carbs += (ingredient.getCarbohydrates() == -1d) ? 0d : ingredient.getCarbohydrates();
                fat += (ingredient.getFat() == -1d) ? 0d : ingredient.getFat();
                cholesterol += (ingredient.getCholesterol() == -1d) ? 0d : ingredient.getCholesterol();
            }
            
            
        }
        
        this.summaryCaloriesLabel.setText(formatDoubleValueForTableVisualisation(calories));
        this.summaryProteinLabel.setText(formatDoubleValueForTableVisualisation(protein));
        this.summarySugarLabel.setText(formatDoubleValueForTableVisualisation(sugar));
        this.summaryCarbsLabel.setText(formatDoubleValueForTableVisualisation(carbs));
        this.summaryFatLabel.setText(formatDoubleValueForTableVisualisation(fat));
        this.summaryCholesterolLabel.setText(formatDoubleValueForTableVisualisation(cholesterol));
    }
    
    private void viewIngredientTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewIngredientTableMouseClicked
        final int[] selectedRowIndexes = this.viewIngredientTable.getSelectedRows();
        this.calculateSummaryFromSelectedRows(selectedRowIndexes);
    }//GEN-LAST:event_viewIngredientTableMouseClicked

    private void viewIngredientTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_viewIngredientTableKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.viewIngredientTable.clearSelection();
            this.resetSummaryLabels();
        }
    }//GEN-LAST:event_viewIngredientTableKeyReleased

    private void buildViewIngredientsTableWith(final List<NutritionalIngredient> ingredientsToAdd) {
        final DefaultTableModel viewIngredientTableModel = (DefaultTableModel) viewIngredientTable.getModel();
        viewIngredientTableModel.setRowCount(0);
        
        ingredientsToAdd.forEach(ingredient -> viewIngredientTableModel.addRow(sanitizeIngredientRowDataForTable(ingredient)));
    }
    
    private void resetViewIngredientTable() {
        ((DefaultTableModel) viewIngredientTable.getModel()).setRowCount(0);
    }
    
    private void fillViewIngredientTable() {
        try {
            this.ingredients = this.dao.ingredients();
            if (this.ingredients != null && !this.ingredients.isEmpty()) {
                this.buildViewIngredientsTableWith(this.ingredients);
            }
        } catch (final SQLException ex) {
            this.showError("Error with the database", ex);
        }
    }
    
    private Object[] sanitizeIngredientRowDataForTable(final NutritionalIngredient ingredient) {
        final Object[] ingredientRowData = {
              ingredient.getId()
            , ingredient.getName()
            , ingredient.getGrams()
            , ingredient.getCalories() == -1d ? "" : formatDoubleValueForTableVisualisation(ingredient.getCalories())
            , ingredient.getFat() == -1d ? "" : formatDoubleValueForTableVisualisation(ingredient.getFat())
            , ingredient.getSugar() == -1d ? "" : formatDoubleValueForTableVisualisation(ingredient.getSugar())
            , ingredient.getCarbohydrates() == -1d ? "" : formatDoubleValueForTableVisualisation(ingredient.getCarbohydrates())
            , ingredient.getProtein() == -1d ? "" : formatDoubleValueForTableVisualisation(ingredient.getProtein())
            , ingredient.getCholesterol() == -1d ? "" : formatDoubleValueForTableVisualisation(ingredient.getCholesterol())
            , ingredient.getSodium() == -1d ? "" : formatDoubleValueForTableVisualisation(ingredient.getSodium())
            , ingredient.getCategory()
            , ingredient.getNotes()
        };
        
        return ingredientRowData;
    }
    
    private void fillUpCategories() {
        try {
            this.categories = this.dao.categories();
            this.categories.forEach(category -> this.newIngredientCategoryList.addItem(category.getName()));
        } catch (final SQLException ex) {
            // TODO: improve error handling here.
            ex.printStackTrace();
        }
    }
    
    public static void main(final String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> new MainWindow().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addIngredientMenuItem;
    private javax.swing.JButton addNewIngredientButton;
    private javax.swing.JDialog addNewIngredientDialog;
    private javax.swing.JButton clearNewIngredientButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenuItem;
    private javax.swing.JMenu ingredientsMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField newIngredientCaloriesField;
    private javax.swing.JLabel newIngredientCaloriesLabel;
    private javax.swing.JTextField newIngredientCarbohydratesField;
    private javax.swing.JLabel newIngredientCarbohydratesLabel;
    private javax.swing.JLabel newIngredientCategoryLabel;
    private javax.swing.JComboBox<String> newIngredientCategoryList;
    private javax.swing.JTextField newIngredientCholesterolField;
    private javax.swing.JLabel newIngredientCholesterolLabel;
    private javax.swing.JTextField newIngredientFatField;
    private javax.swing.JLabel newIngredientFatLabel;
    private javax.swing.JTextField newIngredientGramsField;
    private javax.swing.JLabel newIngredientGramsLabel;
    private javax.swing.JLabel newIngredientNameLabel;
    private javax.swing.JTextField newIngredientNameText;
    private javax.swing.JTextField newIngredientNotesField;
    private javax.swing.JTextField newIngredientProteinField;
    private javax.swing.JLabel newIngredientProteinLabel;
    private javax.swing.JTextField newIngredientSodiumField;
    private javax.swing.JLabel newIngredientSodiumLabel;
    private javax.swing.JTextField newIngredientSugarField;
    private javax.swing.JLabel newIngredientSugarLabel;
    private javax.swing.JButton okViewIngredientButton;
    private javax.swing.JLabel searchViewIngredientLabel;
    private javax.swing.JTextField searchViewIngredientTextField;
    private javax.swing.JLabel summaryCaloriesLabel;
    private javax.swing.JLabel summaryCarbsLabel;
    private javax.swing.JLabel summaryCholesterolLabel;
    private javax.swing.JLabel summaryFatLabel;
    private javax.swing.JLabel summaryProteinLabel;
    private javax.swing.JLabel summaryStaticCaloriesLabel;
    private javax.swing.JLabel summaryStaticCarbsLabel;
    private javax.swing.JLabel summaryStaticCholesterolLabel;
    private javax.swing.JLabel summaryStaticFatLabel;
    private javax.swing.JLabel summaryStaticProteinLabel;
    private javax.swing.JLabel summaryStaticSugarLabel;
    private javax.swing.JLabel summarySugarLabel;
    private javax.swing.JPanel valuesSummaryPanel;
    private javax.swing.JDialog viewIngredientDialog;
    private javax.swing.JMenuItem viewIngredientMenuItem;
    private javax.swing.JTable viewIngredientTable;
    // End of variables declaration//GEN-END:variables
}
