package howmanycals;

import howmanycals.db.dao.HowManyCalsDAO;
import howmanycals.domain.Category;
import howmanycals.domain.Meal;
import howmanycals.domain.MealNutritionInformation;
import howmanycals.domain.Note;
import howmanycals.domain.NutritionalIngredient;
import howmanycals.domain.email.MealEmail;
import howmanycals.utils.EmailSenderUtil;
import howmanycals.utils.InformationMissingAnalysisUtil;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UIManager;
import howmanycals.utils.MealEmailHTMLFormatter;
import howmanycals.utils.SummaryUtil;
import java.io.IOException;

import static howmanycals.utils.FormatUtils.formatDecimal1;
import javax.swing.JSlider;

public class MainWindow extends JFrame {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MainWindow.class.getSimpleName());
    private static final DateTimeFormatter CREATION_TIME_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private HowManyCalsDAO dao;
    private List<Category> categories;
    private List<NutritionalIngredient> ingredients;
    private boolean editIngredientMode = false;
    private int editIngredientID = -1;
    private NutritionalIngredient ingredientForAnalysisWithSlider = null;
    private boolean debugEnabled = false;

    private void initDatabase() {
        this.dao = new HowManyCalsDAO();
        this.dao.init();
    }

    public MainWindow() {
        this.initDatabase();
        this.initComponents();
        this.postComponentsSetup();
        this.initConfig();
    }
    
    private void initConfig() {
        final String debugEnvVariable = System.getenv("DEBUG_CALS");
        if (debugEnvVariable != null) {
            this.debugEnabled = Boolean.valueOf(debugEnvVariable);
            System.out.println("Value is: " + debugEnabled);
        }
    }
    
    private void createKeybindings(final MainWindow window) {
        window.notesTable.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        window.notesTable.getActionMap().put("Enter", new EnterKeyTableActionImpl(this));
    }
    
    private void postComponentsSetup() {
        // ...
        this.createKeybindings(this);
    }
    
    private Optional<NutritionalIngredient> findIngredientByIndex(
            final int index
            , final List<NutritionalIngredient> nutritionIngredients) {
        return nutritionIngredients.stream()
                .filter(ing -> ing.getId() == index)
                .findFirst();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addEditIngredientDialog = new javax.swing.JDialog();
        newIngredientNameLabel = new javax.swing.JLabel();
        newIngredientNameField = new javax.swing.JTextField();
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
        saveIngredientButton = new javax.swing.JButton();
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
        jScrollPane3 = new javax.swing.JScrollPane();
        selectedMealTable = new javax.swing.JTable();
        saveMealButton = new javax.swing.JButton();
        byCategorySearchComboBox = new javax.swing.JComboBox<>();
        byCategorySearchIngredientsCheckBox = new javax.swing.JCheckBox();
        editSelectedIngredientButton = new javax.swing.JButton();
        clearViewIngredientTableButton = new javax.swing.JButton();
        saveMealDialog = new javax.swing.JDialog();
        saveMealNameLabel = new javax.swing.JLabel();
        saveMealTextField = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        saveNotesTextArea = new javax.swing.JTextArea();
        saveNotesLabel = new javax.swing.JLabel();
        saveMealButtonDialog = new javax.swing.JButton();
        cancelMealSaveButton = new javax.swing.JButton();
        saveAndSendByEmailButton = new javax.swing.JButton();
        viewMealsDialog = new javax.swing.JDialog();
        viewMealSearchLabel = new javax.swing.JLabel();
        viewMealsSearchMealTextField = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        viewSelectedMealTable = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        viewMealsTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        viewMealsFoundLabel = new javax.swing.JLabel();
        mealSummaryTabbedPanel = new javax.swing.JTabbedPane();
        mealSummaryPanel = new javax.swing.JPanel();
        staticCaloriesSummaryMealLabel = new javax.swing.JLabel();
        caloriesSummaryMealLabel = new javax.swing.JLabel();
        staticProteinSummaryMealLabel = new javax.swing.JLabel();
        proteinSummaryMealLabel = new javax.swing.JLabel();
        staticCarbsSummaryMealLabel = new javax.swing.JLabel();
        carbsSummaryMealLabel = new javax.swing.JLabel();
        staticSugarSummaryMealLabel = new javax.swing.JLabel();
        sugarSummaryMealLabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        staticFatSummaryMealLabel = new javax.swing.JLabel();
        staticCholesterolSummaryMealLabel = new javax.swing.JLabel();
        cholesterolSummaryMealLabel = new javax.swing.JLabel();
        notesDialog = new javax.swing.JDialog();
        jScrollPane7 = new javax.swing.JScrollPane();
        notesTable = new javax.swing.JTable();
        closeNotesDialogButton = new javax.swing.JButton();
        fileNoteMenuBar = new javax.swing.JMenuBar();
        notesFileMenu = new javax.swing.JMenu();
        notesNewMenuItem = new javax.swing.JMenuItem();
        viewNoteDialog = new javax.swing.JDialog();
        closeViewNoteDialogButton = new javax.swing.JButton();
        staticCateViewNoteDialogLabel = new javax.swing.JLabel();
        dateViewNoteDialogLabel = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        noteViewNoteDialogTextArea = new javax.swing.JTextArea();
        createNoteDialog = new javax.swing.JDialog();
        jScrollPane9 = new javax.swing.JScrollPane();
        newNoteTextPane = new javax.swing.JTextPane();
        saveNewNoteButton = new javax.swing.JButton();
        dataMissingDialog = new javax.swing.JDialog();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        fatPercentageMissingRateLabel = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        sugarPercentageMissingRateLabel = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        carbsPercentageMissingRateLabel = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        proteinPercentageMissingRateLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cholesterolPercentageMissingRateLabel = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        sodiumPercentageMissingRateLabel = new javax.swing.JLabel();
        okCloseDataMissingDialogButton = new javax.swing.JButton();
        ingredientSlideAnalysis = new javax.swing.JDialog();
        ingredientSlider = new javax.swing.JSlider();
        gramsAnalysisStaticLabel = new javax.swing.JLabel();
        gramsSliderStaticLabel = new javax.swing.JLabel();
        gramsSliderDynamicLabel = new javax.swing.JLabel();
        caloriesSliderStaticLabel = new javax.swing.JLabel();
        caloriesSliderDynamicLabel = new javax.swing.JLabel();
        closeSliderAnalysisButton = new javax.swing.JButton();
        viewMealsButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenuItem = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        ingredientsMenuItem = new javax.swing.JMenu();
        addIngredientMenuItem = new javax.swing.JMenuItem();
        viewIngredientMenuItem = new javax.swing.JMenuItem();
        notesMenu = new javax.swing.JMenu();
        notesMenuItem = new javax.swing.JMenuItem();
        createNoteMenuItem = new javax.swing.JMenuItem();
        dataMenu = new javax.swing.JMenu();
        dataMissingAnalysisButton = new javax.swing.JMenuItem();
        aboutMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        addEditIngredientDialog.setTitle("Add Ingredient");
        addEditIngredientDialog.setMinimumSize(new java.awt.Dimension(430, 540));
        addEditIngredientDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        addEditIngredientDialog.setResizable(false);
        addEditIngredientDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                addEditIngredientDialogWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                addEditIngredientDialogWindowOpened(evt);
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

        saveIngredientButton.setMnemonic('S');
        saveIngredientButton.setText("Save");
        saveIngredientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveIngredientButtonActionPerformed(evt);
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

        javax.swing.GroupLayout addEditIngredientDialogLayout = new javax.swing.GroupLayout(addEditIngredientDialog.getContentPane());
        addEditIngredientDialog.getContentPane().setLayout(addEditIngredientDialogLayout);
        addEditIngredientDialogLayout.setHorizontalGroup(
            addEditIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addEditIngredientDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addEditIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addEditIngredientDialogLayout.createSequentialGroup()
                        .addComponent(newIngredientNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(addEditIngredientDialogLayout.createSequentialGroup()
                        .addComponent(newIngredientGramsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(addEditIngredientDialogLayout.createSequentialGroup()
                        .addComponent(newIngredientCaloriesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(newIngredientFatLabel)
                    .addGroup(addEditIngredientDialogLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(newIngredientSugarLabel))
                    .addComponent(newIngredientCarbohydratesLabel)
                    .addComponent(newIngredientProteinLabel)
                    .addComponent(newIngredientSodiumLabel)
                    .addGroup(addEditIngredientDialogLayout.createSequentialGroup()
                        .addComponent(newIngredientCategoryLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addComponent(newIngredientCholesterolLabel)
                    .addGroup(addEditIngredientDialogLayout.createSequentialGroup()
                        .addComponent(saveIngredientButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearNewIngredientButton))
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addGroup(addEditIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newIngredientCategoryList, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newIngredientSodiumField, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(newIngredientCholesterolField)
                    .addComponent(newIngredientProteinField)
                    .addComponent(newIngredientCarbohydratesField)
                    .addComponent(newIngredientSugarField)
                    .addComponent(newIngredientFatField)
                    .addComponent(newIngredientCaloriesField)
                    .addComponent(newIngredientGramsField)
                    .addComponent(newIngredientNameField)
                    .addComponent(newIngredientNotesField))
                .addGap(21, 21, 21))
        );
        addEditIngredientDialogLayout.setVerticalGroup(
            addEditIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addEditIngredientDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addEditIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientNameLabel)
                    .addComponent(newIngredientNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addEditIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientGramsLabel)
                    .addComponent(newIngredientGramsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addEditIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientCaloriesLabel)
                    .addComponent(newIngredientCaloriesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addEditIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientFatLabel)
                    .addComponent(newIngredientFatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addEditIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientSugarLabel)
                    .addComponent(newIngredientSugarField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addEditIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientCarbohydratesLabel)
                    .addComponent(newIngredientCarbohydratesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addEditIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientProteinLabel)
                    .addComponent(newIngredientProteinField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addEditIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientCholesterolLabel)
                    .addComponent(newIngredientCholesterolField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addEditIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIngredientSodiumLabel)
                    .addComponent(newIngredientSodiumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addEditIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addEditIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(newIngredientCategoryLabel)
                        .addComponent(jLabel4))
                    .addComponent(newIngredientCategoryList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addEditIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newIngredientNotesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(addEditIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveIngredientButton)
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
        viewIngredientDialog.setMinimumSize(new java.awt.Dimension(1200, 900));
        viewIngredientDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        viewIngredientDialog.setResizable(false);
        viewIngredientDialog.setSize(new java.awt.Dimension(1200, 900));
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
        viewIngredientTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        viewIngredientTable.getTableHeader().setReorderingAllowed(false);
        viewIngredientTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                viewIngredientTableMousePressed(evt);
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

        searchViewIngredientLabel.setDisplayedMnemonic('e');
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

        summaryCaloriesLabel.setFont(new java.awt.Font("Noto Sans", 1, 13)); // NOI18N

        summaryProteinLabel.setFont(new java.awt.Font("Noto Sans", 1, 13)); // NOI18N

        summaryCarbsLabel.setFont(new java.awt.Font("Noto Sans", 1, 13)); // NOI18N

        summarySugarLabel.setFont(new java.awt.Font("Noto Sans", 1, 13)); // NOI18N

        summaryFatLabel.setFont(new java.awt.Font("Noto Sans", 1, 13)); // NOI18N

        summaryCholesterolLabel.setFont(new java.awt.Font("Noto Sans", 1, 13)); // NOI18N

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

        selectedMealTable.setModel(new javax.swing.table.DefaultTableModel(
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
        selectedMealTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        selectedMealTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                selectedMealTableMousePressed(evt);
            }
        });
        selectedMealTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                selectedMealTableKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(selectedMealTable);
        if (selectedMealTable.getColumnModel().getColumnCount() > 0) {
            selectedMealTable.getColumnModel().getColumn(0).setMinWidth(30);
            selectedMealTable.getColumnModel().getColumn(0).setMaxWidth(30);
            selectedMealTable.getColumnModel().getColumn(1).setResizable(false);
            selectedMealTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            selectedMealTable.getColumnModel().getColumn(2).setMinWidth(50);
            selectedMealTable.getColumnModel().getColumn(2).setMaxWidth(50);
            selectedMealTable.getColumnModel().getColumn(3).setMinWidth(70);
            selectedMealTable.getColumnModel().getColumn(3).setMaxWidth(70);
            selectedMealTable.getColumnModel().getColumn(4).setMinWidth(50);
            selectedMealTable.getColumnModel().getColumn(4).setMaxWidth(50);
            selectedMealTable.getColumnModel().getColumn(5).setMinWidth(50);
            selectedMealTable.getColumnModel().getColumn(5).setMaxWidth(50);
            selectedMealTable.getColumnModel().getColumn(6).setMinWidth(102);
            selectedMealTable.getColumnModel().getColumn(6).setMaxWidth(102);
            selectedMealTable.getColumnModel().getColumn(7).setMinWidth(80);
            selectedMealTable.getColumnModel().getColumn(7).setMaxWidth(80);
            selectedMealTable.getColumnModel().getColumn(8).setMinWidth(90);
            selectedMealTable.getColumnModel().getColumn(8).setMaxWidth(90);
            selectedMealTable.getColumnModel().getColumn(9).setMinWidth(70);
            selectedMealTable.getColumnModel().getColumn(9).setMaxWidth(70);
        }

        saveMealButton.setMnemonic('S');
        saveMealButton.setText("Save Meal");
        saveMealButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMealButtonActionPerformed(evt);
            }
        });

        byCategorySearchComboBox.setEnabled(false);
        byCategorySearchComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byCategorySearchComboBoxActionPerformed(evt);
            }
        });

        byCategorySearchIngredientsCheckBox.setMnemonic('C');
        byCategorySearchIngredientsCheckBox.setText("By Category");
        byCategorySearchIngredientsCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byCategorySearchIngredientsCheckBoxActionPerformed(evt);
            }
        });

        editSelectedIngredientButton.setMnemonic('E');
        editSelectedIngredientButton.setText("Edit Ingredient");
        editSelectedIngredientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSelectedIngredientButtonActionPerformed(evt);
            }
        });

        clearViewIngredientTableButton.setMnemonic('C');
        clearViewIngredientTableButton.setText("Clear");
        clearViewIngredientTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearViewIngredientTableButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewIngredientDialogLayout = new javax.swing.GroupLayout(viewIngredientDialog.getContentPane());
        viewIngredientDialog.getContentPane().setLayout(viewIngredientDialogLayout);
        viewIngredientDialogLayout.setHorizontalGroup(
            viewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewIngredientDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewIngredientDialogLayout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(viewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewIngredientDialogLayout.createSequentialGroup()
                                .addComponent(editSelectedIngredientButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                                .addComponent(okViewIngredientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(viewIngredientDialogLayout.createSequentialGroup()
                                .addComponent(clearViewIngredientTableButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(saveMealButton))))
                    .addGroup(viewIngredientDialogLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(viewIngredientDialogLayout.createSequentialGroup()
                        .addComponent(searchViewIngredientLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchViewIngredientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(byCategorySearchIngredientsCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(byCategorySearchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(searchViewIngredientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(byCategorySearchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(byCategorySearchIngredientsCheckBox))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewIngredientDialogLayout.createSequentialGroup()
                        .addGroup(viewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveMealButton)
                            .addComponent(clearViewIngredientTableButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(viewIngredientDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(okViewIngredientButton)
                            .addComponent(editSelectedIngredientButton)))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        saveMealDialog.setTitle("Save meal?");
        saveMealDialog.setMinimumSize(new java.awt.Dimension(466, 215));
        saveMealDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        saveMealDialog.setResizable(false);

        saveMealNameLabel.setDisplayedMnemonic('M');
        saveMealNameLabel.setLabelFor(saveMealTextField);
        saveMealNameLabel.setText("Meal Name:");

        saveNotesTextArea.setColumns(20);
        saveNotesTextArea.setRows(5);
        jScrollPane4.setViewportView(saveNotesTextArea);

        saveNotesLabel.setDisplayedMnemonic('N');
        saveNotesLabel.setLabelFor(saveNotesTextArea);
        saveNotesLabel.setText("Notes (optional):");

        saveMealButtonDialog.setMnemonic('s');
        saveMealButtonDialog.setText("Save");
        saveMealButtonDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMealButtonDialogActionPerformed(evt);
            }
        });

        cancelMealSaveButton.setMnemonic('c');
        cancelMealSaveButton.setText("Cancel");
        cancelMealSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelMealSaveButtonActionPerformed(evt);
            }
        });

        saveAndSendByEmailButton.setMnemonic('E');
        saveAndSendByEmailButton.setText("Save and send by Email");
        saveAndSendByEmailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAndSendByEmailButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout saveMealDialogLayout = new javax.swing.GroupLayout(saveMealDialog.getContentPane());
        saveMealDialog.getContentPane().setLayout(saveMealDialogLayout);
        saveMealDialogLayout.setHorizontalGroup(
            saveMealDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(saveMealDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(saveMealDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                    .addGroup(saveMealDialogLayout.createSequentialGroup()
                        .addComponent(saveMealNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveMealTextField))
                    .addGroup(saveMealDialogLayout.createSequentialGroup()
                        .addComponent(saveNotesLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, saveMealDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelMealSaveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveMealButtonDialog)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveAndSendByEmailButton)))
                .addContainerGap())
        );
        saveMealDialogLayout.setVerticalGroup(
            saveMealDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(saveMealDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(saveMealDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveMealNameLabel)
                    .addComponent(saveMealTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(saveMealDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(saveMealDialogLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveNotesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(69, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, saveMealDialogLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(saveMealDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cancelMealSaveButton)
                            .addComponent(saveMealButtonDialog)
                            .addComponent(saveAndSendByEmailButton))
                        .addContainerGap())))
        );

        viewMealsDialog.setTitle("Meals");
        viewMealsDialog.setMinimumSize(new java.awt.Dimension(1200, 700));
        viewMealsDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        viewMealsDialog.setResizable(false);

        viewMealSearchLabel.setDisplayedMnemonic('M');
        viewMealSearchLabel.setLabelFor(viewMealsSearchMealTextField);
        viewMealSearchLabel.setText("Meal:");

        viewMealsSearchMealTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewMealsSearchMealTextFieldActionPerformed(evt);
            }
        });
        viewMealsSearchMealTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                viewMealsSearchMealTextFieldKeyReleased(evt);
            }
        });

        viewSelectedMealTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Grams", "Calories", "Sugar", "Carbohydrates", "Protein", "Cholesterol", "Sodium", "Category", "Notes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(viewSelectedMealTable);
        if (viewSelectedMealTable.getColumnModel().getColumnCount() > 0) {
            viewSelectedMealTable.getColumnModel().getColumn(0).setMinWidth(30);
            viewSelectedMealTable.getColumnModel().getColumn(0).setMaxWidth(30);
            viewSelectedMealTable.getColumnModel().getColumn(1).setResizable(false);
            viewSelectedMealTable.getColumnModel().getColumn(2).setMinWidth(50);
            viewSelectedMealTable.getColumnModel().getColumn(2).setMaxWidth(50);
            viewSelectedMealTable.getColumnModel().getColumn(3).setMinWidth(70);
            viewSelectedMealTable.getColumnModel().getColumn(3).setMaxWidth(70);
            viewSelectedMealTable.getColumnModel().getColumn(4).setMinWidth(50);
            viewSelectedMealTable.getColumnModel().getColumn(4).setMaxWidth(50);
            viewSelectedMealTable.getColumnModel().getColumn(5).setMinWidth(102);
            viewSelectedMealTable.getColumnModel().getColumn(5).setMaxWidth(102);
            viewSelectedMealTable.getColumnModel().getColumn(6).setMinWidth(80);
            viewSelectedMealTable.getColumnModel().getColumn(6).setMaxWidth(80);
            viewSelectedMealTable.getColumnModel().getColumn(7).setMinWidth(90);
            viewSelectedMealTable.getColumnModel().getColumn(7).setMaxWidth(90);
            viewSelectedMealTable.getColumnModel().getColumn(8).setMinWidth(70);
            viewSelectedMealTable.getColumnModel().getColumn(8).setMaxWidth(70);
        }

        viewMealsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Creation Time", "Notes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        viewMealsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewMealsTableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(viewMealsTable);
        if (viewMealsTable.getColumnModel().getColumnCount() > 0) {
            viewMealsTable.getColumnModel().getColumn(0).setMinWidth(35);
            viewMealsTable.getColumnModel().getColumn(0).setMaxWidth(35);
            viewMealsTable.getColumnModel().getColumn(1).setResizable(false);
            viewMealsTable.getColumnModel().getColumn(2).setMinWidth(180);
            viewMealsTable.getColumnModel().getColumn(2).setMaxWidth(180);
        }

        jLabel7.setDisplayedMnemonic('I');
        jLabel7.setLabelFor(viewSelectedMealTable);
        jLabel7.setText("Ingredients:");

        staticCaloriesSummaryMealLabel.setText("Calories:");

        staticProteinSummaryMealLabel.setText("Protein:");

        staticCarbsSummaryMealLabel.setText("Carbs:");

        staticSugarSummaryMealLabel.setText("Sugar:");

        jLabel12.setText("Fat:");

        staticCholesterolSummaryMealLabel.setText("Cholesterol:");

        javax.swing.GroupLayout mealSummaryPanelLayout = new javax.swing.GroupLayout(mealSummaryPanel);
        mealSummaryPanel.setLayout(mealSummaryPanelLayout);
        mealSummaryPanelLayout.setHorizontalGroup(
            mealSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mealSummaryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mealSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(staticCaloriesSummaryMealLabel)
                    .addComponent(staticProteinSummaryMealLabel)
                    .addComponent(staticCarbsSummaryMealLabel)
                    .addComponent(staticSugarSummaryMealLabel)
                    .addComponent(jLabel12)
                    .addComponent(staticCholesterolSummaryMealLabel))
                .addGap(66, 66, 66)
                .addGroup(mealSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(proteinSummaryMealLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addComponent(caloriesSummaryMealLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(carbsSummaryMealLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sugarSummaryMealLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(staticFatSummaryMealLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cholesterolSummaryMealLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(711, Short.MAX_VALUE))
        );
        mealSummaryPanelLayout.setVerticalGroup(
            mealSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mealSummaryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mealSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(staticCaloriesSummaryMealLabel)
                    .addComponent(caloriesSummaryMealLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mealSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(staticProteinSummaryMealLabel)
                    .addComponent(proteinSummaryMealLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mealSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(staticCarbsSummaryMealLabel)
                    .addComponent(carbsSummaryMealLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mealSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(staticSugarSummaryMealLabel)
                    .addComponent(sugarSummaryMealLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mealSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(staticFatSummaryMealLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mealSummaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(staticCholesterolSummaryMealLabel)
                    .addComponent(cholesterolSummaryMealLabel))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        mealSummaryTabbedPanel.addTab("Summary", mealSummaryPanel);

        javax.swing.GroupLayout viewMealsDialogLayout = new javax.swing.GroupLayout(viewMealsDialog.getContentPane());
        viewMealsDialog.getContentPane().setLayout(viewMealsDialogLayout);
        viewMealsDialogLayout.setHorizontalGroup(
            viewMealsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewMealsDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewMealsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane6)
                    .addGroup(viewMealsDialogLayout.createSequentialGroup()
                        .addGroup(viewMealsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewMealsDialogLayout.createSequentialGroup()
                                .addComponent(viewMealSearchLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(viewMealsSearchMealTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(viewMealsDialogLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(viewMealsFoundLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(mealSummaryTabbedPanel))
                .addContainerGap())
        );
        viewMealsDialogLayout.setVerticalGroup(
            viewMealsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewMealsDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewMealsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewMealSearchLabel)
                    .addComponent(viewMealsSearchMealTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(viewMealsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(viewMealsFoundLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mealSummaryTabbedPanel)
                .addContainerGap())
        );

        notesDialog.setTitle("Notes");
        notesDialog.setMinimumSize(new java.awt.Dimension(850, 600));
        notesDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        notesDialog.setResizable(false);
        notesDialog.setSize(new java.awt.Dimension(850, 600));

        notesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Note", "Created"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        notesTable.setToolTipText("Enter to view");
        notesTable.setShowVerticalLines(false);
        notesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                notesTableMousePressed(evt);
            }
        });
        jScrollPane7.setViewportView(notesTable);
        if (notesTable.getColumnModel().getColumnCount() > 0) {
            notesTable.getColumnModel().getColumn(0).setMinWidth(40);
            notesTable.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        closeNotesDialogButton.setMnemonic('C');
        closeNotesDialogButton.setText("Close");
        closeNotesDialogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeNotesDialogButtonActionPerformed(evt);
            }
        });

        notesFileMenu.setMnemonic('F');
        notesFileMenu.setText("File");

        notesNewMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        notesNewMenuItem.setMnemonic('N');
        notesNewMenuItem.setText("New");
        notesNewMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notesNewMenuItemActionPerformed(evt);
            }
        });
        notesFileMenu.add(notesNewMenuItem);

        fileNoteMenuBar.add(notesFileMenu);

        notesDialog.setJMenuBar(fileNoteMenuBar);

        javax.swing.GroupLayout notesDialogLayout = new javax.swing.GroupLayout(notesDialog.getContentPane());
        notesDialog.getContentPane().setLayout(notesDialogLayout);
        notesDialogLayout.setHorizontalGroup(
            notesDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notesDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(notesDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, notesDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(closeNotesDialogButton)))
                .addContainerGap())
        );
        notesDialogLayout.setVerticalGroup(
            notesDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notesDialogLayout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(closeNotesDialogButton)
                .addContainerGap())
        );

        viewNoteDialog.setTitle("Note");
        viewNoteDialog.setMinimumSize(new java.awt.Dimension(400, 300));
        viewNoteDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        viewNoteDialog.setResizable(false);
        viewNoteDialog.setSize(new java.awt.Dimension(400, 300));

        closeViewNoteDialogButton.setMnemonic('C');
        closeViewNoteDialogButton.setText("Close");
        closeViewNoteDialogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeViewNoteDialogButtonActionPerformed(evt);
            }
        });

        staticCateViewNoteDialogLabel.setLabelFor(dateViewNoteDialogLabel);
        staticCateViewNoteDialogLabel.setText("Date:");

        dateViewNoteDialogLabel.setForeground(new java.awt.Color(67, 85, 193));

        noteViewNoteDialogTextArea.setColumns(20);
        noteViewNoteDialogTextArea.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        noteViewNoteDialogTextArea.setForeground(new java.awt.Color(5, 5, 14));
        noteViewNoteDialogTextArea.setRows(5);
        noteViewNoteDialogTextArea.setTabSize(4);
        noteViewNoteDialogTextArea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        noteViewNoteDialogTextArea.setCaretColor(new java.awt.Color(254, 254, 254));
        noteViewNoteDialogTextArea.setDisabledTextColor(new java.awt.Color(1, 1, 1));
        noteViewNoteDialogTextArea.setDragEnabled(true);
        noteViewNoteDialogTextArea.setOpaque(false);
        noteViewNoteDialogTextArea.setSelectedTextColor(new java.awt.Color(179, 7, 7));
        jScrollPane8.setViewportView(noteViewNoteDialogTextArea);

        javax.swing.GroupLayout viewNoteDialogLayout = new javax.swing.GroupLayout(viewNoteDialog.getContentPane());
        viewNoteDialog.getContentPane().setLayout(viewNoteDialogLayout);
        viewNoteDialogLayout.setHorizontalGroup(
            viewNoteDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewNoteDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewNoteDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewNoteDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(closeViewNoteDialogButton))
                    .addGroup(viewNoteDialogLayout.createSequentialGroup()
                        .addComponent(staticCateViewNoteDialogLabel)
                        .addGap(18, 18, 18)
                        .addComponent(dateViewNoteDialogLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        viewNoteDialogLayout.setVerticalGroup(
            viewNoteDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewNoteDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewNoteDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(staticCateViewNoteDialogLabel)
                    .addComponent(dateViewNoteDialogLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeViewNoteDialogButton)
                .addContainerGap())
        );

        createNoteDialog.setTitle("Create Note");
        createNoteDialog.setMinimumSize(new java.awt.Dimension(400, 300));
        createNoteDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        createNoteDialog.setResizable(false);
        createNoteDialog.setSize(new java.awt.Dimension(400, 300));

        newNoteTextPane.setNextFocusableComponent(saveNewNoteButton);
        jScrollPane9.setViewportView(newNoteTextPane);

        saveNewNoteButton.setMnemonic('S');
        saveNewNoteButton.setText("Save");
        saveNewNoteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveNewNoteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout createNoteDialogLayout = new javax.swing.GroupLayout(createNoteDialog.getContentPane());
        createNoteDialog.getContentPane().setLayout(createNoteDialogLayout);
        createNoteDialogLayout.setHorizontalGroup(
            createNoteDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createNoteDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(createNoteDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createNoteDialogLayout.createSequentialGroup()
                        .addGap(0, 337, Short.MAX_VALUE)
                        .addComponent(saveNewNoteButton)))
                .addContainerGap())
        );
        createNoteDialogLayout.setVerticalGroup(
            createNoteDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createNoteDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveNewNoteButton)
                .addContainerGap())
        );

        dataMissingDialog.setTitle("Data % missing rates");
        dataMissingDialog.setMinimumSize(new java.awt.Dimension(403, 228));
        dataMissingDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        dataMissingDialog.setResizable(false);
        dataMissingDialog.setSize(new java.awt.Dimension(403, 228));

        jLabel9.setText("% information missing for each category in the DB.");

        jLabel10.setText("Fat %");

        jLabel13.setText("Sugar %");

        jLabel15.setText("Carbs %");

        jLabel17.setText("Protein %");

        jLabel8.setText("Cholesterol %");

        jLabel20.setText("Sodium %");

        okCloseDataMissingDialogButton.setMnemonic('O');
        okCloseDataMissingDialogButton.setText("OK");
        okCloseDataMissingDialogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okCloseDataMissingDialogButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dataMissingDialogLayout = new javax.swing.GroupLayout(dataMissingDialog.getContentPane());
        dataMissingDialog.getContentPane().setLayout(dataMissingDialogLayout);
        dataMissingDialogLayout.setHorizontalGroup(
            dataMissingDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataMissingDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataMissingDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(dataMissingDialogLayout.createSequentialGroup()
                        .addGroup(dataMissingDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))
                        .addGap(45, 45, 45)
                        .addGroup(dataMissingDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(proteinPercentageMissingRateLabel)
                            .addComponent(sugarPercentageMissingRateLabel)
                            .addComponent(fatPercentageMissingRateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carbsPercentageMissingRateLabel)))
                    .addGroup(dataMissingDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dataMissingDialogLayout.createSequentialGroup()
                            .addComponent(jLabel20)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sodiumPercentageMissingRateLabel))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dataMissingDialogLayout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(18, 18, 18)
                            .addComponent(cholesterolPercentageMissingRateLabel))))
                .addContainerGap(79, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataMissingDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okCloseDataMissingDialogButton)
                .addContainerGap())
        );
        dataMissingDialogLayout.setVerticalGroup(
            dataMissingDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataMissingDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dataMissingDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(fatPercentageMissingRateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dataMissingDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(sugarPercentageMissingRateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dataMissingDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(carbsPercentageMissingRateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dataMissingDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(proteinPercentageMissingRateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dataMissingDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cholesterolPercentageMissingRateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dataMissingDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(sodiumPercentageMissingRateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okCloseDataMissingDialogButton)
                .addContainerGap())
        );

        ingredientSlideAnalysis.setTitle("But, what if ...");
        ingredientSlideAnalysis.setMinimumSize(new java.awt.Dimension(450, 300));
        ingredientSlideAnalysis.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        ingredientSlideAnalysis.setResizable(false);

        ingredientSlider.setMaximum(350);
        ingredientSlider.setPaintLabels(true);
        ingredientSlider.setPaintTicks(true);
        ingredientSlider.setNextFocusableComponent(closeSliderAnalysisButton);
        ingredientSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ingredientSliderStateChanged(evt);
            }
        });

        gramsAnalysisStaticLabel.setFont(new java.awt.Font("Noto Sans", 1, 13)); // NOI18N
        gramsAnalysisStaticLabel.setText("Grams / calorie analysis");
        gramsAnalysisStaticLabel.setNextFocusableComponent(ingredientSlider);

        gramsSliderStaticLabel.setText("Grams:");

        gramsSliderDynamicLabel.setText("-");

        caloriesSliderStaticLabel.setText("Calories:");

        caloriesSliderDynamicLabel.setText("-");

        closeSliderAnalysisButton.setMnemonic('c');
        closeSliderAnalysisButton.setText("Close");
        closeSliderAnalysisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeSliderAnalysisButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ingredientSlideAnalysisLayout = new javax.swing.GroupLayout(ingredientSlideAnalysis.getContentPane());
        ingredientSlideAnalysis.getContentPane().setLayout(ingredientSlideAnalysisLayout);
        ingredientSlideAnalysisLayout.setHorizontalGroup(
            ingredientSlideAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ingredientSlideAnalysisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ingredientSlideAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ingredientSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                    .addGroup(ingredientSlideAnalysisLayout.createSequentialGroup()
                        .addGroup(ingredientSlideAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gramsAnalysisStaticLabel)
                            .addGroup(ingredientSlideAnalysisLayout.createSequentialGroup()
                                .addComponent(gramsSliderStaticLabel)
                                .addGap(90, 90, 90)
                                .addComponent(gramsSliderDynamicLabel))
                            .addGroup(ingredientSlideAnalysisLayout.createSequentialGroup()
                                .addComponent(caloriesSliderStaticLabel)
                                .addGap(82, 82, 82)
                                .addComponent(caloriesSliderDynamicLabel)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ingredientSlideAnalysisLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(closeSliderAnalysisButton)))
                .addContainerGap())
        );
        ingredientSlideAnalysisLayout.setVerticalGroup(
            ingredientSlideAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ingredientSlideAnalysisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gramsAnalysisStaticLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ingredientSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ingredientSlideAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gramsSliderStaticLabel)
                    .addComponent(gramsSliderDynamicLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ingredientSlideAnalysisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(caloriesSliderStaticLabel)
                    .addComponent(caloriesSliderDynamicLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(closeSliderAnalysisButton)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HowManyCals v0.1");

        viewMealsButton.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        viewMealsButton.setForeground(new java.awt.Color(5, 70, 254));
        viewMealsButton.setMnemonic('V');
        viewMealsButton.setText("View Meals");
        viewMealsButton.setOpaque(true);
        viewMealsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewMealsButtonActionPerformed(evt);
            }
        });

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

        notesMenu.setMnemonic('N');
        notesMenu.setText("Notes");

        notesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        notesMenuItem.setText("Notes");
        notesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notesMenuItemActionPerformed(evt);
            }
        });
        notesMenu.add(notesMenuItem);

        createNoteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        createNoteMenuItem.setText("Create Note");
        createNoteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNoteMenuItemActionPerformed(evt);
            }
        });
        notesMenu.add(createNoteMenuItem);

        menuBar.add(notesMenu);

        dataMenu.setMnemonic('D');
        dataMenu.setText("Data");

        dataMissingAnalysisButton.setText("Data Missing Analysis");
        dataMissingAnalysisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataMissingAnalysisButtonActionPerformed(evt);
            }
        });
        dataMenu.add(dataMissingAnalysisButton);

        menuBar.add(dataMenu);

        aboutMenu.setText("About");

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        aboutMenu.add(aboutMenuItem);

        menuBar.add(aboutMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewMealsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(330, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewMealsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(237, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        try {
            if (this.dao != null) {
                this.dao.tearDown();
            }
        } catch (final SQLException ex) {
            LOGGER.error("error closing DB connection", ex);
        }
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void addIngredientMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addIngredientMenuItemActionPerformed
        this.clearNewIngredientDialogFields();
        this.editIngredientMode = false;
        this.addEditIngredientDialog.setTitle("Add Ingredient");
        this.addEditIngredientDialog.setVisible(true);
    }//GEN-LAST:event_addIngredientMenuItemActionPerformed

    private void addEditIngredientDialogWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_addEditIngredientDialogWindowOpened
        if (this.categories != null) {
            this.categories.clear();
        }
        try {
            this.fillUpCategories();
        } catch (final SQLException ex) {
            LOGGER.error("Error getting categories", ex);
            this.showError("Error retrieving information from DB", ex);
        }
    }//GEN-LAST:event_addEditIngredientDialogWindowOpened

    private void createIngredient(final NutritionalIngredient ingredient) throws SQLException {
        
    }
    
    private Optional<NutritionalIngredient> updateIngredient(final NutritionalIngredient ingredient) throws SQLException {
        if (this.editIngredientID == -1) {
            throw new RuntimeException("missing ID to edit.");
        }
        
        final String name = ingredient.getName().trim();
        final var ingredientInDB = this.dao.findIngredientByName(name);
        
        // Check if there is an ingredient with that name already.
        if (ingredientInDB.isPresent() && (ingredientInDB.get().getId() != this.editIngredientID)) {
            throw new SQLException("There is an ingredient with that name already.");
        }
        
        System.out.println(String.format("Updating: %d, [%s]", this.editIngredientID, ingredient));
        
        return this.dao.updateIngredient(ingredient);
    }
    
    private void saveIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveIngredientButtonActionPerformed
        final String name = this.newIngredientNameField.getText();
        final String grams = this.newIngredientGramsField.getText();
        final String calories = this.newIngredientCaloriesField.getText();
        final String fat = this.newIngredientFatField.getText();
        final String sugar = this.newIngredientSugarField.getText();
        final String carbs = this.newIngredientCarbohydratesField.getText();
        final String protein = this.newIngredientProteinField.getText();
        final String cholesterol = this.newIngredientCholesterolField.getText();
        final String sodium = this.newIngredientSodiumField.getText();
        final int selectedIndex = this.newIngredientCategoryList.getSelectedIndex();
        final Category selectedCategory = this.categories.get(selectedIndex);
        final String notes = this.newIngredientNotesField.getText();
        
        final NutritionalIngredient.FormBuilder builder = new NutritionalIngredient.FormBuilder(name, grams);
            
        final NutritionalIngredient ingredient = builder
                .calories(calories)
                .fat(fat)
                .sugar(sugar)
                .carbohydrates(carbs)
                .protein(protein)
                .cholesterol(cholesterol)
                .sodium(sodium)
                .category(selectedCategory)
                .notes(notes)
            .build();
    
        try {
            if (this.editIngredientMode) {
                final var updatedIngredient = this.updateIngredient(ingredient);
                if (updatedIngredient.isPresent()) {
                    LOGGER.debug(String.format("Ingredient updated to -> [%s]", updatedIngredient.get()));
                } else {
                    this.showError("Error updating ingredient.", "Error updating ingredient.");
                }
                this.editIngredientMode = false;
                this.editIngredientID = -1;
                
                return;
            }
            
            this.dao.createNutritionIngredient(ingredient).ifPresent(dbIngredient -> {
                this.showInfoMessage(String.format("'%s' added correctly.", dbIngredient.getName()), "Ingredient added to database.");
                this.addEditIngredientDialog.setVisible(false);
                this.editIngredientMode = false;
                this.editIngredientID = -1;
            });
        } catch (final NumberFormatException ex) {
            LOGGER.error(ex.getMessage(), ex);
            this.showError("Missing or invalid field format", ex);
        } catch (final SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
            this.showError("Error creating ingredient", ex);
        }
    }//GEN-LAST:event_saveIngredientButtonActionPerformed

    private void showError(final String message, final String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    private void showError(final String message, final Throwable cause) {
        JOptionPane.showMessageDialog(this, cause.getMessage(), message, JOptionPane.ERROR_MESSAGE);
    }
    
    private void showInfoMessage(final String message, final String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void clearNewIngredientDialogFields() {
        this.newIngredientNameField.setText("");
        this.newIngredientGramsField.setText("");
        this.newIngredientCaloriesField.setText("");
        this.newIngredientFatField.setText("");
        this.newIngredientSugarField.setText("");
        this.newIngredientCarbohydratesField.setText("");
        this.newIngredientProteinField.setText("");
        this.newIngredientCholesterolField.setText("");
        this.newIngredientSodiumField.setText("");
        this.newIngredientNotesField.setText("");
    }
    
    private void clearNewIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearNewIngredientButtonActionPerformed
        this.clearNewIngredientDialogFields();
    }//GEN-LAST:event_clearNewIngredientButtonActionPerformed

    private void newIngredientCategoryListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newIngredientCategoryListActionPerformed
        final int selectedIndex = this.newIngredientCategoryList.getSelectedIndex();
        final Category selectedCateory = this.categories.get(selectedIndex);
        System.out.println(selectedCateory);
        
    }//GEN-LAST:event_newIngredientCategoryListActionPerformed

    private void populateByCategorySelector() throws SQLException {
        final List<Category> ingredientCategories = this.dao.categories();
        ingredientCategories.forEach(category -> this.byCategorySearchComboBox.addItem(category.getName()));
    }
    
    private void viewIngredientMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewIngredientMenuItemActionPerformed
        try {
            this.resetViewIngredientTable();
            this.fillViewIngredientTable();
            this.resetSummaryLabels();
            this.populateByCategorySelector();
            this.viewIngredientDialog.setVisible(true);
        } catch (final SQLException ex) {
            LOGGER.error("Error with the database", ex);
            this.showError("Error with the database", ex);
        }
    }//GEN-LAST:event_viewIngredientMenuItemActionPerformed

    private void okViewIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okViewIngredientButtonActionPerformed
        this.viewIngredientDialog.setVisible(false);
    }//GEN-LAST:event_okViewIngredientButtonActionPerformed

    private void searchViewIngredientTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchViewIngredientTextFieldActionPerformed
        System.out.println("Enter?...");
    }//GEN-LAST:event_searchViewIngredientTextFieldActionPerformed

    private void viewIngredientDialogWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_viewIngredientDialogWindowOpened
        try {
            this.resetViewIngredientTable();
            this.fillViewIngredientTable();
        } catch (final SQLException ex) {
            this.showError("Error with the database", ex);
        }
    }//GEN-LAST:event_viewIngredientDialogWindowOpened

    private void viewIngredientDialogKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_viewIngredientDialogKeyPressed
        System.out.println("Key closed ... ");
    }//GEN-LAST:event_viewIngredientDialogKeyPressed

    private void rebuildViewTableFromSearchText(
            final String searchText
            , final JTable table
            , final List<NutritionalIngredient> nutritionIngredients) {
        if (this.ingredients == null || this.ingredients.isEmpty()) {
            LOGGER.debug("ingredients empty ... nothing to do here ... ");
            return;
        }
        
        ((DefaultTableModel) table.getModel()).setRowCount(0);
        
        final List<NutritionalIngredient> ingredientsContainingSearchText = nutritionIngredients.stream()
                .filter(ingredient -> ingredient.getName().toLowerCase().contains(searchText)).collect(Collectors.toList());
        
        this.buildTableWithIngredients(ingredientsContainingSearchText, table);
    }
    
    private void searchViewIngredientTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchViewIngredientTextFieldKeyReleased
        final String searchText = this.searchViewIngredientTextField.getText();
        if (searchText.isEmpty()) {
            if (!this.ingredients.isEmpty()) {
                // this.resetSummaryLabels();
                this.buildTableWithIngredients(this.ingredients, this.viewIngredientTable);
            }
            return;
        }
        
        this.rebuildViewTableFromSearchText(searchText.toLowerCase(), this.viewIngredientTable, this.ingredients);
    }//GEN-LAST:event_searchViewIngredientTextFieldKeyReleased

    private void resetSummaryLabels() {
        this.summaryCaloriesLabel.setText("");
        this.summaryProteinLabel.setText("");
        this.summarySugarLabel.setText("");
        this.summaryCarbsLabel.setText("");
        this.summaryFatLabel.setText("");
        this.summaryCholesterolLabel.setText("");
    }
    
    private Optional<MealNutritionInformation> calculateSummaryFromTable(
            final JTable table
            , final List<NutritionalIngredient> nutIngredients) {
        
        if (ingredients.isEmpty()) {
            return Optional.empty();
        }
        
        double calories = 0d;
        double protein = 0d;
        double sugar = 0d;
        double carbs = 0d;
        double fat = 0d;
        double cholesterol = 0d;
        
        final DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        
        if (this.debugEnabled) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>");
        }
        
        final int rowCount = tableModel.getRowCount();
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            final int dbIdx = (Integer) tableModel.getValueAt(rowIndex, 0);
            
            final Optional<NutritionalIngredient> dbIngredient = 
                    nutIngredients.stream().filter(ingredient -> ingredient.getId() == dbIdx).findAny();
            if (dbIngredient.isPresent()) {
                final NutritionalIngredient ingredient = dbIngredient.get();
                calories += (ingredient.getCalories() == -1d) ? 0d : ingredient.getCalories();
                protein += (ingredient.getProtein() == -1d) ? 0d : ingredient.getProtein();
                sugar += (ingredient.getSugar() == -1d) ? 0d : ingredient.getSugar();
                carbs += (ingredient.getCarbohydrates() == -1d) ? 0d : ingredient.getCarbohydrates();
                fat += (ingredient.getFat() == -1d) ? 0d : ingredient.getFat();
                cholesterol += (ingredient.getCholesterol() == -1d) ? 0d : ingredient.getCholesterol();
                if (this.debugEnabled) {
                    System.out.printf("%50s - %.3f\n", ingredient.getName(), ingredient.getCalories());
                }
            }
        }
        
        if (this.debugEnabled) {
            System.out.printf("%50s ~ [%.2f]\n", "TOTAL", calories);
        }
        
        final MealNutritionInformation mealNutritionInformation = new MealNutritionInformation();
        mealNutritionInformation.setCalories(calories);
        mealNutritionInformation.setProtein(protein);
        mealNutritionInformation.setSugar(sugar);
        mealNutritionInformation.setCarbohydrates(carbs);
        mealNutritionInformation.setFat(fat);
        mealNutritionInformation.setCholesterol(cholesterol);
        
        return Optional.of(mealNutritionInformation);
    }
    
    private void calculateSummaryFromSelectedRows() {
        final Optional<MealNutritionInformation> summary = 
                this.calculateSummaryFromTable(this.selectedMealTable, this.ingredients);
        
        if (summary.isPresent()) {
            final MealNutritionInformation mealNutritionFacts = summary.get();
            this.summaryCaloriesLabel.setText(formatDecimal1(mealNutritionFacts.getCalories()));
            this.summaryProteinLabel.setText(formatDecimal1(mealNutritionFacts.getProtein()));
            this.summarySugarLabel.setText(formatDecimal1(mealNutritionFacts.getSugar()));
            this.summaryCarbsLabel.setText(formatDecimal1(mealNutritionFacts.getCarbohydrates()));
            this.summaryFatLabel.setText(formatDecimal1(mealNutritionFacts.getFat()));
            this.summaryCholesterolLabel.setText(formatDecimal1(mealNutritionFacts.getCholesterol()));
        }
    }
    
    private void viewIngredientTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_viewIngredientTableKeyReleased
        final int code = evt.getKeyCode();
        switch (code) {
            case KeyEvent.VK_ESCAPE -> {
                this.viewIngredientTable.clearSelection();
                this.resetSummaryLabels();
            }
            case KeyEvent.VK_S -> {
                // Clean the slider if present:
                this.ingredientForAnalysisWithSlider = null;
                
                final JTable table = (JTable) evt.getSource();
                final int selectedRow = table.getSelectedRow();
                
                if (selectedRow == -1) {
                    return;
                }
                
                final DefaultTableModel model = (DefaultTableModel) table.getModel();
                final int ingredientID = (Integer) model.getValueAt(selectedRow, 0);
                
                try {
                    final Optional<NutritionalIngredient> ingredientInDB = this.dao.findById(ingredientID);
                    if (ingredientInDB.isPresent()) {
                        System.out.println("Ingredient found ... ");
                        this.ingredientForAnalysisWithSlider = null;
                        this.ingredientForAnalysisWithSlider = ingredientInDB.get();
                        this.ingredientSlideAnalysis.setTitle(this.ingredientForAnalysisWithSlider.getName());
                        this.prepareValuesForSlider(this.ingredientForAnalysisWithSlider, this.ingredientSlider);
                        this.ingredientSlideAnalysis.setVisible(true);
                    } else {
                        // TODO: do something here...
                    }
                } catch (final SQLException ex) {
                    // TODO: fix this:
                    ex.printStackTrace();
                }
        
                
                System.out.println("The S key ...");
            }
        }
    }//GEN-LAST:event_viewIngredientTableKeyReleased
    
    private void viewIngredientTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewIngredientTableMousePressed
        final JTable table = (JTable) evt.getSource();
        
        if (evt.getClickCount() == 2 && table.getSelectedRow() != -1) {
            final Point point = evt.getPoint();
            int row = table.rowAtPoint(point);
            final int nutritionIngredientIdx = (Integer) table.getModel().getValueAt(row, 0);
            
            final Optional<NutritionalIngredient> ingredient = this.findIngredientByIndex(nutritionIngredientIdx, this.ingredients);
            if (ingredient.isPresent()) {
                this.addIngredientToSelectedMeals(ingredient.get());
                this.calculateSummaryFromSelectedRows();
            }
        }
    }//GEN-LAST:event_viewIngredientTableMousePressed

    private void addIngredientToSelectedMeals(final NutritionalIngredient ingredient) {
        final DefaultTableModel tableModel = (DefaultTableModel) this.selectedMealTable.getModel();
        tableModel.addRow(sanitizeIngredientRowDataForTable(ingredient));
    }
    
    private void selectedMealTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectedMealTableMousePressed
        final JTable table =(JTable) evt.getSource();
        final Point point = evt.getPoint();
        
        if (evt.getClickCount() == 2 && table.getSelectedRow() != -1) {
            final int row = table.rowAtPoint(point);
            ((DefaultTableModel) table.getModel()).removeRow(row);
            this.calculateSummaryFromSelectedRows();
        }
    }//GEN-LAST:event_selectedMealTableMousePressed

    private void saveMealButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMealButtonActionPerformed
        this.saveMealDialog.setVisible(true);
    }//GEN-LAST:event_saveMealButtonActionPerformed

    private void cleanMealSaveDialogFields() {
        this.saveMealTextField.setText("");
        this.saveNotesTextArea.setText("");
    }
    
    private void cancelMealSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelMealSaveButtonActionPerformed
        this.saveMealTextField.setText("");
        this.saveNotesTextArea.setText("");
        this.saveMealDialog.setVisible(false);
    }//GEN-LAST:event_cancelMealSaveButtonActionPerformed

    private void validateFieldsMealSavingDialog() throws SQLException {
        final String mealName = this.saveMealTextField.getText().trim();
        if (mealName.isBlank()) {
            throw new IllegalArgumentException("meal name empty");
        }
        
        final Optional<Meal> mealInDB = this.dao.findMealByName(mealName.toLowerCase());
        if (mealInDB.isPresent()) {
            throw new IllegalArgumentException(String.format("Meal '%s' already exists.", mealName));
        }
        
        final DefaultTableModel tableModel = (DefaultTableModel) this.selectedMealTable.getModel();
        if (tableModel.getRowCount() == 0) {
            throw new IllegalArgumentException("No ingredients selected for meal");
        }
    }
    
    private void saveMealButtonDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMealButtonDialogActionPerformed
        try {
            this.validateFieldsMealSavingDialog();
            
            final String mealName = this.saveMealTextField.getText().trim();
            final DefaultTableModel tableModel = (DefaultTableModel) this.selectedMealTable.getModel();
            final List<Integer> ids = new ArrayList<>();

            for (int row = 0; row < tableModel.getRowCount(); row++) {
                ids.add((Integer) tableModel.getValueAt(row, 0));
            }

            this.dao.saveMeal(mealName, this.saveNotesTextArea.getText(), ids).ifPresent(meal -> {
                    this.showInfoMessage(String.format("'%s' saved", mealName), "Meal saved");
                    this.saveMealDialog.setVisible(false);
                    this.cleanMealSaveDialogFields();
                }
            );
        } catch (SQLException | IllegalArgumentException ex) {
            this.showError("Error saving meal", ex);
            LOGGER.error(ex.getMessage(), ex);
        }
    }//GEN-LAST:event_saveMealButtonDialogActionPerformed

    private void viewMealsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewMealsButtonActionPerformed
        this.viewMealsSearchMealTextField.setText("");
        this.caloriesSummaryMealLabel.setText("");
        this.proteinSummaryMealLabel.setText("");
        this.sugarSummaryMealLabel.setText("");
        this.carbsSummaryMealLabel.setText("");
        this.staticFatSummaryMealLabel.setText("");
        this.cholesterolSummaryMealLabel.setText("");
        final DefaultTableModel mealsTableModel = (DefaultTableModel) this.viewMealsTable.getModel();
        mealsTableModel.setRowCount(0);
        
        try {
            final List<Meal> meals = this.dao.meals();
            this.populateTableWithMeals(meals, this.viewMealsTable);
            this.viewMealsDialog.setVisible(true);
        } catch (final SQLException ex) {
            LOGGER.error("Error getting meals", ex);
            this.showError("Error getting meals", "Error metting meals");
        }
    }//GEN-LAST:event_viewMealsButtonActionPerformed

    private void viewMealsSearchMealTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewMealsSearchMealTextFieldActionPerformed
        // TODO: 
    }//GEN-LAST:event_viewMealsSearchMealTextFieldActionPerformed

    private void cleanViewMealsTable(final JTable table) {
        ((DefaultTableModel) table.getModel()).setRowCount(0);
    }
    
    private void viewMealsSearchMealTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_viewMealsSearchMealTextFieldKeyReleased
        final String searchText = this.viewMealsSearchMealTextField.getText();
        if (searchText.isEmpty()) {
            return;
        }
        
        System.out.printf("Searching for -> [%s]\n", searchText);
        
        ((DefaultTableModel) this.viewSelectedMealTable.getModel()).setRowCount(0);
        
        this.cleanViewMealsTable(this.viewMealsTable);
        try {
            final String toSearchText = searchText.trim().toLowerCase();
            final List<Meal> mealsByFoundBySearch = this.dao.containsByName(toSearchText.trim().toLowerCase());
            System.out.println(mealsByFoundBySearch);
            this.populateTableWithMeals(mealsByFoundBySearch, this.viewMealsTable);
        } catch (final SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
            this.showError("Error getting meals from the database.", "DB error");
        }
    }//GEN-LAST:event_viewMealsSearchMealTextFieldKeyReleased

    private void populateTableWithMeals(final List<Meal> meals, final JTable table) {
        if (meals.isEmpty()) {
            return;
        }
        
        final DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        meals.forEach(meal -> this.sanitizeMealForTable(tableModel, meal));
    }
    
    private void viewMealsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewMealsTableMouseClicked
        final JTable table = (JTable) evt.getSource();
        ((DefaultTableModel) this.viewSelectedMealTable.getModel()).setRowCount(0);
        
        final Point point = evt.getPoint();
        int row = table.rowAtPoint(point);
        final int nutritionIngredientIdx = (Integer) table.getModel().getValueAt(row, 0);
        
        try {
            final List<NutritionalIngredient> mealIngredients = this.dao.findIngredientsByMealID(nutritionIngredientIdx);
            if (!mealIngredients.isEmpty()) {
                final DefaultTableModel tableModel = (DefaultTableModel) this.viewSelectedMealTable.getModel();
                mealIngredients.forEach(ingredient -> tableModel.addRow(this.sanitizeIngredientRowDataForTable(ingredient)));
                SummaryUtil.calculateSummaryFromIngredients(mealIngredients).ifPresent(mealNutritionFacts -> {
                    this.caloriesSummaryMealLabel.setText(formatDecimal1(mealNutritionFacts.getCalories()));
                    this.proteinSummaryMealLabel.setText(formatDecimal1(mealNutritionFacts.getProtein()));
                    this.sugarSummaryMealLabel.setText(formatDecimal1(mealNutritionFacts.getSugar()));
                    this.carbsSummaryMealLabel.setText(formatDecimal1(mealNutritionFacts.getCarbohydrates()));
                    this.staticFatSummaryMealLabel.setText(formatDecimal1(mealNutritionFacts.getFat()));
                    this.cholesterolSummaryMealLabel.setText(formatDecimal1(mealNutritionFacts.getCholesterol()));
                });
            }
        } catch (final SQLException ex) {
            LOGGER.error("Error getting ingredients for meal.", ex);
            this.showError("Error getting ingreadients for meal.", "Error");
        }
    }//GEN-LAST:event_viewMealsTableMouseClicked

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // TODO: do something here...
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void byCategorySearchComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_byCategorySearchComboBoxActionPerformed
        if (!this.byCategorySearchIngredientsCheckBox.isSelected()) {
            return;
        }
        
        final JComboBox categoriesBox = (JComboBox) evt.getSource();
        final String categoryName = categoriesBox.getSelectedItem().toString();
        try {
            final Optional<Category> selectedCategory = this.dao.findCategoryByName(categoryName);
            if (selectedCategory.isPresent()) {
                final Category cat = selectedCategory.get();
                final List<NutritionalIngredient> nutritionIngredientsByCategory = this.dao.findIngredientsByCategoryID(cat.getId());
                this.buildTableWithIngredients(nutritionIngredientsByCategory, this.viewIngredientTable);
            }
        } catch (final SQLException ex) {
            this.showError("Error getting categories", "Error");
            LOGGER.error("Error connecting to DB", ex);
        }
    }//GEN-LAST:event_byCategorySearchComboBoxActionPerformed

    private void byCategorySearchIngredientsCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_byCategorySearchIngredientsCheckBoxActionPerformed
        final JCheckBox checkBox = (JCheckBox) evt.getSource();
        this.byCategorySearchComboBox.setEnabled(checkBox.isSelected());
        
        try {
            if (checkBox.isEnabled() == false) {
                this.ingredients = this.dao.ingredients();
                this.buildTableWithIngredients(this.ingredients, this.viewIngredientTable);
            } else {
                this.resetViewIngredientTable();
                this.buildTableWithIngredients(this.ingredients, this.viewIngredientTable);
            }
        } catch (final SQLException ex) {
            this.showError("Error connecting to the database", "ERROR");
            LOGGER.error("Error connecting to the database", ex);
        }
    }//GEN-LAST:event_byCategorySearchIngredientsCheckBoxActionPerformed

    private void resetNotesDialog() {
        final JTable notesTable = this.notesTable;
        ((DefaultTableModel) notesTable.getModel()).setRowCount(0);
    }
    
    private void notesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notesMenuItemActionPerformed
        try {
            this.resetNotesDialog();
            final List<Note> notes = this.dao.notes();
            final DefaultTableModel tableModel = (DefaultTableModel) this.notesTable.getModel();
            notes.forEach(note -> tableModel.addRow(this.sanitizeNoteForTable(note)));
            this.notesDialog.setVisible(true);
        } catch (final SQLException ex) {
            this.showError("Error getting notes", "Error");
            LOGGER.error("Error getting notes from database.", ex);
        }
    }//GEN-LAST:event_notesMenuItemActionPerformed

    private void notesNewMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notesNewMenuItemActionPerformed
        System.out.println("Create new note ... ");
    }//GEN-LAST:event_notesNewMenuItemActionPerformed

    private void closeNotesDialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeNotesDialogButtonActionPerformed
        this.notesDialog.setVisible(false);
    }//GEN-LAST:event_closeNotesDialogButtonActionPerformed

    private void closeViewNoteDialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeViewNoteDialogButtonActionPerformed
        this.viewNoteDialog.setVisible(false);
    }//GEN-LAST:event_closeViewNoteDialogButtonActionPerformed

    private void createNoteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNoteMenuItemActionPerformed
        this.newNoteTextPane.setText("");
        this.createNoteDialog.setVisible(true);
        System.out.println("");
    }//GEN-LAST:event_createNoteMenuItemActionPerformed

    private void saveNewNoteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveNewNoteButtonActionPerformed
        if (this.newNoteTextPane.getText().isBlank()) {
            return;
        }
        
        final String noteText = this.newNoteTextPane.getText();
        try {
            final Optional<Note> noteSaved = this.dao.createNote(noteText);
            if (noteSaved.isPresent()) {
                this.createNoteDialog.setVisible(false);
            }
        } catch (final SQLException ex) {
            this.showError("Error creating note", "Error");
            LOGGER.error("error creating note", ex);
        }
        
    }//GEN-LAST:event_saveNewNoteButtonActionPerformed

    private void resetPercentageRateMissingFields() {
        this.fatPercentageMissingRateLabel.setText("");
        this.sugarPercentageMissingRateLabel.setText("");
        this.carbsPercentageMissingRateLabel.setText("");
        this.cholesterolPercentageMissingRateLabel.setText("");
        this.sodiumPercentageMissingRateLabel.setText("");
        this.proteinPercentageMissingRateLabel.setText("");
    }
    
    private void formatePercentageMissingRateLabels(final InformationMissingAnalysisUtil.MissingPercentageRates rates) {
        this.fatPercentageMissingRateLabel.setText(formatDecimal1(rates.fat()));
        this.sugarPercentageMissingRateLabel.setText(formatDecimal1(rates.sugar()));
        this.carbsPercentageMissingRateLabel.setText(formatDecimal1(rates.carbs()));
        this.cholesterolPercentageMissingRateLabel.setText(formatDecimal1(rates.cholesterol()));
        this.sodiumPercentageMissingRateLabel.setText(formatDecimal1(rates.sodium()));
        this.proteinPercentageMissingRateLabel.setText(formatDecimal1(rates.protein()));
    }
    
    private void dataMissingAnalysisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataMissingAnalysisButtonActionPerformed
        try {
            if (this.ingredients == null || this.ingredients.isEmpty()) {
                this.ingredients = this.dao.ingredients();
            }

            this.resetPercentageRateMissingFields();

            final InformationMissingAnalysisUtil.MissingPercentageRates missingPercentageRates = 
                    new InformationMissingAnalysisUtil().rates(this.ingredients);

            this.formatePercentageMissingRateLabels(missingPercentageRates);
            
            this.dataMissingDialog.setVisible(true);
        } catch (final SQLException ex) {
            this.showError("Error getting ingredients from the database.", "Error");
            LOGGER.error("Error getting ingredients from the database.", ex);
        }
    }//GEN-LAST:event_dataMissingAnalysisButtonActionPerformed

    private void okCloseDataMissingDialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okCloseDataMissingDialogButtonActionPerformed
        this.dataMissingDialog.setVisible(false);
    }//GEN-LAST:event_okCloseDataMissingDialogButtonActionPerformed

    private void selectedMealTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_selectedMealTableKeyPressed
        if (evt.getKeyCode() != KeyEvent.VK_DELETE || this.selectedMealTable.getSelectedRow() < 0) {
            return;
        }
        
        final int selectedRow = this.selectedMealTable.getSelectedRow();
        
        final DefaultTableModel tableModel = (DefaultTableModel) this.selectedMealTable.getModel();
        tableModel.removeRow(selectedRow);
        
        this.calculateSummaryFromSelectedRows();
    }//GEN-LAST:event_selectedMealTableKeyPressed

    private void notesTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notesTableMousePressed
        final JTable table = (JTable) evt.getSource();
        
        if (evt.getClickCount() == 2 && table.getSelectedRow() != -1) {
            final DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            final int selectedRow = table.getSelectedRow();
            
            final Integer noteID = (Integer) tableModel.getValueAt(selectedRow, 0);
            
            try {
                this.dao.findNoteById(noteID).ifPresent(note -> {
                    this.dateViewNoteDialogLabel.setText(note.getCreationDate().format(CREATION_TIME_FORMATTER));
                    this.noteViewNoteDialogTextArea.setText(note.getNote());
                    this.viewNoteDialog.setVisible(true);
                });
            } catch (final SQLException ex) {
                this.showError("Error getting note", "Error");
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }//GEN-LAST:event_notesTableMousePressed

    private void setEditAddIngredientFields(final NutritionalIngredient ingredient) {
        this.newIngredientNameField.setText(ingredient.getName());
        this.newIngredientGramsField.setText(ingredient.getGrams() + "");
        
        final String cals = ingredient.getCalories() != -1d ? formatDecimal1(ingredient.getCalories()) : "";
        this.newIngredientCaloriesField.setText(cals);
        
        final String fat = ingredient.getFat() != -1d ? formatDecimal1(ingredient.getFat()) : "";
        this.newIngredientFatField.setText(fat);
        
        final String sugar = ingredient.getSugar() != -1d ? formatDecimal1(ingredient.getSugar()) : "";
        this.newIngredientSugarField.setText(sugar);
        
        final String carbs = ingredient.getCarbohydrates() != -1d ? formatDecimal1(ingredient.getCarbohydrates()) : "";
        this.newIngredientCarbohydratesField.setText(carbs);
        
        final String protein = ingredient.getProtein() != -1d ? formatDecimal1(ingredient.getProtein()) : "";
        this.newIngredientProteinField.setText(protein);
        
        final String cholesterol = ingredient.getCholesterol() != -1d ? formatDecimal1(ingredient.getCholesterol()) : "";
        this.newIngredientCholesterolField.setText(cholesterol);
        
        final String sodium = ingredient.getSodium() != -1d ? formatDecimal1(ingredient.getSodium()) : "";
        this.newIngredientSodiumField.setText(sodium);
        
        // TODO: Category...
        this.newIngredientNotesField.setText(ingredient.getNotes());
    }
    
    private void editSelectedIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSelectedIngredientButtonActionPerformed
        final int selectedRow = this.viewIngredientTable.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        
        // grab the ID:
        final DefaultTableModel tableModel = (DefaultTableModel) this.viewIngredientTable.getModel();
        final int ingredientID = (Integer) tableModel.getValueAt(selectedRow, 0);
        
        try {
            final Optional<NutritionalIngredient> ingredientToEdit = this.dao.findById(ingredientID);
            if (ingredientToEdit.isPresent()) {
                this.addEditIngredientDialog.setTitle("Edit Ingredient");
                this.setEditAddIngredientFields(ingredientToEdit.get());
                this.editIngredientMode = true;
                this.editIngredientID = ingredientID;
                this.addEditIngredientDialog.setVisible(true);
            }
        } catch (final SQLException ex) {
            this.showError("Error getting ingredient to edit.", "Error");
            LOGGER.error("Error getting ingredient to edit.", ex);
        }
        
    }//GEN-LAST:event_editSelectedIngredientButtonActionPerformed

    private void addEditIngredientDialogWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_addEditIngredientDialogWindowClosed
        this.editIngredientMode = false;
        this.editIngredientID = -1;
    }//GEN-LAST:event_addEditIngredientDialogWindowClosed

    private void saveAndSendByEmailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAndSendByEmailButtonActionPerformed
        try {
            this.validateFieldsMealSavingDialog();
            
            final String mealName = this.saveMealTextField.getText().trim();
            final DefaultTableModel tableModel = (DefaultTableModel) this.selectedMealTable.getModel();
            final List<Integer> ids = new ArrayList<>();

            for (int row = 0; row < tableModel.getRowCount(); row++) {
                ids.add((Integer) tableModel.getValueAt(row, 0));
            }

            final Optional<Meal> meal = this.dao.saveMeal(mealName, this.saveNotesTextArea.getText(), ids);
            if (meal.isPresent()) {
                this.showInfoMessage(String.format("'%s' saved", mealName), "Meal saved");
                final Optional<Meal> mealDB = this.dao.findMealByID(meal.get().getId());
                if (mealDB.isPresent()) {
                    final String mealEmailBody = MealEmailHTMLFormatter.format(mealDB.get());
                    final MealEmail mealEmail = new MealEmail.Builder()
                            .from("leogutierrezramirez@gmail.com")
                            .to("leogutierrezramirez@gmail.com")
                            .subject(String.format("😋🤤 %s, delicious! 🤤😋", mealName))
                            .content("text/html", mealEmailBody)
                            .build();

                    final var emailResponse = EmailSenderUtil.send(mealEmail);
                    
                    LOGGER.debug(String.format("Response code: %d", emailResponse.getStatusCode()));
                    LOGGER.debug(String.format("Response body: %s", emailResponse.getBody()));
                    
                    this.saveMealDialog.setVisible(false);
                    this.cleanMealSaveDialogFields();
                }
            }
        } catch (SQLException | IllegalArgumentException | IOException ex) {
            this.showError("Error saving meal", ex);
            LOGGER.error(ex.getMessage(), ex);
        }
    }//GEN-LAST:event_saveAndSendByEmailButtonActionPerformed

    private void clearViewIngredientTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearViewIngredientTableButtonActionPerformed
        this.searchViewIngredientTextField.setText("");
        this.resetSummaryLabels();
        ((DefaultTableModel) this.viewIngredientTable.getModel()).setRowCount(0);
        ((DefaultTableModel) this.selectedMealTable.getModel()).setRowCount(0);
    }//GEN-LAST:event_clearViewIngredientTableButtonActionPerformed

    private void prepareValuesForSlider(final NutritionalIngredient ingredient, final JSlider slider) {
        // Note: it might need some adjustments ...
        slider.setMaximum(ingredient.getGrams() * 2);
        slider.setValue(ingredient.getGrams());
        this.gramsSliderDynamicLabel.setText(ingredient.getGrams() + "  g");
        this.caloriesSliderDynamicLabel.setText((int) ingredient.getCalories() + " kcal");
    }
    
    private void ingredientSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ingredientSliderStateChanged
        if (!this.ingredientSlider.getValueIsAdjusting() && (this.ingredientForAnalysisWithSlider != null)) {
            final int baseGrams = this.ingredientForAnalysisWithSlider.getGrams();
            final int baseCals = (int) this.ingredientForAnalysisWithSlider.getCalories();
            
            final int newCals = (this.ingredientSlider.getValue() * baseCals) / (int)baseGrams;
            this.caloriesSliderDynamicLabel.setText(newCals + " kcal");
            this.gramsSliderDynamicLabel.setText(this.ingredientSlider.getValue() + " g");
        }
    }//GEN-LAST:event_ingredientSliderStateChanged

    private void closeSliderAnalysisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeSliderAnalysisButtonActionPerformed
        this.ingredientSlideAnalysis.setVisible(false);
        this.ingredientForAnalysisWithSlider = null;
    }//GEN-LAST:event_closeSliderAnalysisButtonActionPerformed

    private void buildTableWithIngredients(final List<NutritionalIngredient> ingredientsToAdd, final JTable table) {
        final DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        
        ingredientsToAdd.forEach(ingredient -> tableModel.addRow(sanitizeIngredientRowDataForTable(ingredient)));
    }
    
    private void resetViewIngredientTable() {
        ((DefaultTableModel) this.viewIngredientTable.getModel()).setRowCount(0);
        ((DefaultTableModel) this.selectedMealTable.getModel()).setRowCount(0);
    }
    
    private void fillViewIngredientTable() throws SQLException {
        this.ingredients = this.dao.ingredients();
        if (this.ingredients != null && !this.ingredients.isEmpty()) {
            this.buildTableWithIngredients(this.ingredients, this.viewIngredientTable);
        }
    }
    
    private Object[] sanitizeIngredientRowDataForTable(final NutritionalIngredient ingredient) {
        final Object[] ingredientRowData = {
              ingredient.getId()
            , ingredient.getName()
            , ingredient.getGrams()
            , ingredient.getCalories() == -1d ? "" : formatDecimal1(ingredient.getCalories())
            , ingredient.getSugar() == -1d ? "" : formatDecimal1(ingredient.getSugar())
            , ingredient.getCarbohydrates() == -1d ? "" : formatDecimal1(ingredient.getCarbohydrates())
            , ingredient.getProtein() == -1d ? "" : formatDecimal1(ingredient.getProtein())
            , ingredient.getCholesterol() == -1d ? "" : formatDecimal1(ingredient.getCholesterol())
            , ingredient.getSodium() == -1d ? "" : formatDecimal1(ingredient.getSodium())
            , ingredient.getCategory().getName()
            , ingredient.getNotes()
        };
        
        return ingredientRowData;
    }
    
    private Object[] sanitizeNoteForTable(final Note note) {
        final Object[] rowData = {
            note.getId()
           , note.getNote()
           , note.getCreationDate().format(CREATION_TIME_FORMATTER)
        };
        
        return rowData;
    }
    
    private void fillUpCategories() throws SQLException {
        this.categories = this.dao.categories();
        this.categories.forEach(category -> this.newIngredientCategoryList.addItem(category.getName()));
    }
    
    public static void main(final String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (final LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (info.getName().equals(howmanycals.gui.GUI.LOOK)) {
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
    private javax.swing.JMenu aboutMenu;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JDialog addEditIngredientDialog;
    private javax.swing.JMenuItem addIngredientMenuItem;
    private javax.swing.JComboBox<String> byCategorySearchComboBox;
    private javax.swing.JCheckBox byCategorySearchIngredientsCheckBox;
    private javax.swing.JLabel caloriesSliderDynamicLabel;
    private javax.swing.JLabel caloriesSliderStaticLabel;
    private javax.swing.JLabel caloriesSummaryMealLabel;
    private javax.swing.JButton cancelMealSaveButton;
    private javax.swing.JLabel carbsPercentageMissingRateLabel;
    private javax.swing.JLabel carbsSummaryMealLabel;
    private javax.swing.JLabel cholesterolPercentageMissingRateLabel;
    private javax.swing.JLabel cholesterolSummaryMealLabel;
    private javax.swing.JButton clearNewIngredientButton;
    private javax.swing.JButton clearViewIngredientTableButton;
    private javax.swing.JButton closeNotesDialogButton;
    private javax.swing.JButton closeSliderAnalysisButton;
    private javax.swing.JButton closeViewNoteDialogButton;
    private javax.swing.JDialog createNoteDialog;
    private javax.swing.JMenuItem createNoteMenuItem;
    private javax.swing.JMenu dataMenu;
    private javax.swing.JMenuItem dataMissingAnalysisButton;
    private javax.swing.JDialog dataMissingDialog;
    private javax.swing.JLabel dateViewNoteDialogLabel;
    private javax.swing.JButton editSelectedIngredientButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JLabel fatPercentageMissingRateLabel;
    private javax.swing.JMenu fileMenuItem;
    private javax.swing.JMenuBar fileNoteMenuBar;
    private javax.swing.JLabel gramsAnalysisStaticLabel;
    private javax.swing.JLabel gramsSliderDynamicLabel;
    private javax.swing.JLabel gramsSliderStaticLabel;
    private javax.swing.JDialog ingredientSlideAnalysis;
    private javax.swing.JSlider ingredientSlider;
    private javax.swing.JMenu ingredientsMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel mealSummaryPanel;
    private javax.swing.JTabbedPane mealSummaryTabbedPanel;
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
    private javax.swing.JTextField newIngredientNameField;
    private javax.swing.JLabel newIngredientNameLabel;
    private javax.swing.JTextField newIngredientNotesField;
    private javax.swing.JTextField newIngredientProteinField;
    private javax.swing.JLabel newIngredientProteinLabel;
    private javax.swing.JTextField newIngredientSodiumField;
    private javax.swing.JLabel newIngredientSodiumLabel;
    private javax.swing.JTextField newIngredientSugarField;
    private javax.swing.JLabel newIngredientSugarLabel;
    private javax.swing.JTextPane newNoteTextPane;
    private javax.swing.JTextArea noteViewNoteDialogTextArea;
    private javax.swing.JDialog notesDialog;
    private javax.swing.JMenu notesFileMenu;
    private javax.swing.JMenu notesMenu;
    private javax.swing.JMenuItem notesMenuItem;
    private javax.swing.JMenuItem notesNewMenuItem;
    private javax.swing.JTable notesTable;
    private javax.swing.JButton okCloseDataMissingDialogButton;
    private javax.swing.JButton okViewIngredientButton;
    private javax.swing.JLabel proteinPercentageMissingRateLabel;
    private javax.swing.JLabel proteinSummaryMealLabel;
    private javax.swing.JButton saveAndSendByEmailButton;
    private javax.swing.JButton saveIngredientButton;
    private javax.swing.JButton saveMealButton;
    private javax.swing.JButton saveMealButtonDialog;
    private javax.swing.JDialog saveMealDialog;
    private javax.swing.JLabel saveMealNameLabel;
    private javax.swing.JTextField saveMealTextField;
    private javax.swing.JButton saveNewNoteButton;
    private javax.swing.JLabel saveNotesLabel;
    private javax.swing.JTextArea saveNotesTextArea;
    private javax.swing.JLabel searchViewIngredientLabel;
    private javax.swing.JTextField searchViewIngredientTextField;
    private javax.swing.JTable selectedMealTable;
    private javax.swing.JLabel sodiumPercentageMissingRateLabel;
    private javax.swing.JLabel staticCaloriesSummaryMealLabel;
    private javax.swing.JLabel staticCarbsSummaryMealLabel;
    private javax.swing.JLabel staticCateViewNoteDialogLabel;
    private javax.swing.JLabel staticCholesterolSummaryMealLabel;
    private javax.swing.JLabel staticFatSummaryMealLabel;
    private javax.swing.JLabel staticProteinSummaryMealLabel;
    private javax.swing.JLabel staticSugarSummaryMealLabel;
    private javax.swing.JLabel sugarPercentageMissingRateLabel;
    private javax.swing.JLabel sugarSummaryMealLabel;
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
    private javax.swing.JLabel viewMealSearchLabel;
    private javax.swing.JButton viewMealsButton;
    private javax.swing.JDialog viewMealsDialog;
    private javax.swing.JLabel viewMealsFoundLabel;
    private javax.swing.JTextField viewMealsSearchMealTextField;
    private javax.swing.JTable viewMealsTable;
    private javax.swing.JDialog viewNoteDialog;
    private javax.swing.JTable viewSelectedMealTable;
    // End of variables declaration//GEN-END:variables

    private void sanitizeMealForTable(final DefaultTableModel tableModel, final Meal meal) {
        final Object[] mealRowData = {
              meal.getId()
            , meal.getName()
            , meal.getCreationDate().format(CREATION_TIME_FORMATTER)
            , meal.getNotes()
        };
        tableModel.addRow(mealRowData);
    }

    private static class EnterKeyTableActionImpl extends AbstractAction {
        
        private final MainWindow window;

        public EnterKeyTableActionImpl(final MainWindow window) {
            this.window = window;
        }

        @Override
        public void actionPerformed(final ActionEvent ae) {
            final JTable table = (JTable) ae.getSource();
            final DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            final int selectedRow = table.getSelectedRow();
            
            final Integer noteID = (Integer) tableModel.getValueAt(selectedRow, 0);
            
            try {
                window.dao.findNoteById(noteID).ifPresent(note -> {
                    this.window.dateViewNoteDialogLabel.setText(note.getCreationDate().format(CREATION_TIME_FORMATTER));
                    this.window.noteViewNoteDialogTextArea.setText(note.getNote());
                    this.window.viewNoteDialog.setVisible(true);
                });
            } catch (final SQLException ex) {
                this.window.showError("Error getting note", "Error");
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }
}
