import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
public class Panel extends JPanel{

    //Menu
    private JMenuBar menuBar = new JMenuBar();
    private JMenu LenguageMenu = new JMenu("Lenguage");
    private JMenuItem RussiaMenuItem = new JMenuItem("Russia");
    private JMenuItem EnglishMenuItem = new JMenuItem("English");
    private JMenuItem EspanolMenuItem = new JMenuItem("Español");

    //Texts
    private JLabel nameProgram = new JLabel("Builder Graph", SwingConstants.CENTER);
    private JLabel y = new JLabel("y =");
    private JLabel meseg_one = new JLabel("<html>Для правильной записи гиперболы<br>" +
            "запишите все её части в скобки, пример:<br>" +
            "y = (k) / (x)<br><br>" +
            "Квадратные уравнения<br>проcьба записывать в виде:<br>ax^2 ± bx ± c<br><br>" +
            "Знак степни записывается как ** либо ^<br>" +
            "корень sqrt(x ± ....)</html>");
    private JLabel meseg_error_brackets = new JLabel(
            "Неверно раставлены скобки",
            SwingConstants.CENTER);
    private JLabel meseg_error_symbolsFilter = new JLabel(
            "Введены недоступные символы",
            SwingConstants.CENTER);
    private JLabel meseg_error_emptyTextField = new JLabel(
            "Введите функцию",
            SwingConstants.CENTER);
    private JLabel meseg_error_invalidSyntaksis = new JLabel(
            "Не правильно поставлены знаки",
            SwingConstants.CENTER);

    // TextField
    private JTextField display = new JTextField("");

    //Buttons
    private JButton Enter_BuildGraph = new JButton("Пострить график");

    //Fonts
    private Font font = new Font("SanSerif", Font.BOLD, 24);
    private Font font_2 = new Font("SanSerif", Font.BOLD, 20);
    private Font font_3 = new Font("SanSerif", Font.BOLD, 18);

    //colors
    private Color color_1 = new Color(178, 217, 255, 255);
    private Color color_2 = new Color(70, 153, 255, 255);
    private Color color_3 = new Color(255, 0, 0);
    public Panel(){
        setLayout(null);
        setBackground(color_2);

        creating_a_language_change_menu();

        // название программы
        nameProgram.setBounds(0, 10, 700, 100);
        nameProgram.setFont(font);
        nameProgram.setVisible(true);
        add(nameProgram);

        // пометка для ввода
        y.setBounds(150, 115, 50, 50);
        y.setFont(font);
        y.setVisible(true);
        add(y);

        // поле ввода
        display.setBounds(200, 115, 350, 50);
        display.setFont(font);
        display.setVisible(true);
        add(display);

        // сообщение для коректности написания
        meseg_one.setBounds(150, 125, 600, 350);
        meseg_one.setFont(font_2);
        meseg_one.setVisible(true);
        add(meseg_one);

        creating_errors_data();

        // кнопка для получения функции
        Enter_BuildGraph.setBounds(200, 650, 300, 100);
        Enter_BuildGraph.setFont(font);
        Enter_BuildGraph.setVisible(true);
        add(Enter_BuildGraph);

        // прослушка для кнопки
        ActionListener buildGraph = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnterBuildGraph();
            }
        };
        Enter_BuildGraph.addActionListener(buildGraph);
    }

    // создание текстов для ошибок и их растановка
    private void creating_errors_data(){
        meseg_error_brackets.setBounds(0, 500, 700, 100);
        meseg_error_brackets.setForeground(color_3);
        meseg_error_brackets.setFont(font);
        meseg_error_brackets.setVisible(false);
        add(meseg_error_brackets);

        meseg_error_symbolsFilter.setBounds(0, 500, 700, 100);
        meseg_error_symbolsFilter.setForeground(color_3);
        meseg_error_symbolsFilter.setFont(font);
        meseg_error_symbolsFilter.setVisible(false);
        add(meseg_error_symbolsFilter);

        meseg_error_emptyTextField.setBounds(0, 500, 700, 100);
        meseg_error_emptyTextField.setForeground(color_3);
        meseg_error_emptyTextField.setFont(font);
        meseg_error_emptyTextField.setVisible(false);
        add(meseg_error_emptyTextField);

        meseg_error_invalidSyntaksis.setBounds(0, 500, 700, 100);
        meseg_error_invalidSyntaksis.setForeground(color_3);
        meseg_error_invalidSyntaksis.setFont(font);
        meseg_error_invalidSyntaksis.setVisible(false);
        add(meseg_error_invalidSyntaksis);
    }

    // создание меню для смены языка
    private void creating_a_language_change_menu(){
        menuBar.setBounds(25, 0, 100, 50);
        menuBar.setBackground(color_1);
        add(menuBar);
        LenguageMenu.setBackground(color_1);
        LenguageMenu.setFont(font_3);
        menuBar.add(LenguageMenu);
        RussiaMenuItem.setBackground(color_1);
        RussiaMenuItem.setFont(font_3);
        LenguageMenu.add(RussiaMenuItem);
        EnglishMenuItem.setBackground(color_1);
        EnglishMenuItem.setFont(font_3);
        LenguageMenu.add(EnglishMenuItem);
        EspanolMenuItem.setBackground(color_1);
        EspanolMenuItem.setFont(font_3);
        LenguageMenu.add(EspanolMenuItem);

        ActionListener RussiaMenuItem_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RussianL();
            }
        };
        ActionListener EnglishMenuItem_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnglishL();
            }
        };
        ActionListener EspanolMenuItem_listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EspanolL();
            }
        };
        RussiaMenuItem.addActionListener(RussiaMenuItem_listener);
        EnglishMenuItem.addActionListener(EnglishMenuItem_listener);
        EspanolMenuItem.addActionListener(EspanolMenuItem_listener);
    }

    /*
    событие для кнопки, передача функции в класс Cont, который
    в свою очередь перенесёт всё в python модуль
    */
    private void EnterBuildGraph(){
        String testFromDisplay = display.getText();
        if(brackets(testFromDisplay) && symbolsFilter(testFromDisplay) &&
                meseg_error_emptyTextField(testFromDisplay) && placementOfSigns(testFromDisplay)){
            meseg_error_brackets.setVisible(false);
            meseg_error_symbolsFilter.setVisible(false);
            meseg_error_emptyTextField.setVisible(false);
            meseg_error_invalidSyntaksis.setVisible(false);
            try {
                Cont.arithmetic_example_Function(testFromDisplay);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            if (!meseg_error_emptyTextField(testFromDisplay)){
                meseg_error_emptyTextField.setVisible(true);
                meseg_error_symbolsFilter.setVisible(false);
                meseg_error_brackets.setVisible(false);
                meseg_error_invalidSyntaksis.setVisible(false);
            }
            else if (!symbolsFilter(testFromDisplay)){
                meseg_error_symbolsFilter.setVisible(true);
                meseg_error_brackets.setVisible(false);
                meseg_error_emptyTextField.setVisible(false);
                meseg_error_invalidSyntaksis.setVisible(false);
            }
            else if (!brackets(testFromDisplay)){
                meseg_error_brackets.setVisible(true);
                meseg_error_symbolsFilter.setVisible(false);
                meseg_error_emptyTextField.setVisible(false);
                meseg_error_invalidSyntaksis.setVisible(false);
            }
            else if (!placementOfSigns(testFromDisplay)){
                meseg_error_invalidSyntaksis.setVisible(true);
                meseg_error_symbolsFilter.setVisible(false);
                meseg_error_emptyTextField.setVisible(false);
                meseg_error_brackets.setVisible(false);
            }
        }
    }

    // проверка на знаки
    private boolean placementOfSigns(String arithmetic_example){
        String[] alphaSnak = {
                "+*", "*+", "+*+", "-*",
                "*-", "-*-", "+/", "/+",
                "+/+", "-/", "/-", "-/-"
        };
        for (String errorSnak: alphaSnak)
            if (arithmetic_example.contains(errorSnak))
                return false;
        if (count("*", arithmetic_example) > 2)
            return false;
        else if (count("/", arithmetic_example) > 2)
            return false;
        return true;
    }

    // счёт количества символов
    private int count(String symbol, String text){
        int res = 0;
        for (int i = 0; i < text.length(); i++)
            if (symbol.contains(text.indexOf(i) + ""))
                res++;
        return res;
    }

    // проверка на скобки
    private boolean brackets(String arithmetic_example){
        String bracket = "";
        for (int i = 0; i < arithmetic_example.length(); i++){
            if (arithmetic_example.charAt(i) == '(' || arithmetic_example.charAt(i) == ')')
                bracket = bracket + arithmetic_example.charAt(i);
        }
        int chetchik = 0;
        for (int i = 0; i < bracket.length(); i++){
            if (bracket.charAt(i) == '(') chetchik++;
            else chetchik--;
            if (chetchik == -1) return false;
        }
        return chetchik == 0;
    }

    // проверка на лишние сиволы
    private boolean symbolsFilter(String arithmetic_example){
        String alpha = "x*-/+^() 1234567890.,";
        for(int i = 0; i < arithmetic_example.length(); i++)
            if (!alpha.contains(arithmetic_example.charAt(i) + "")) {
                if (arithmetic_example.contains("cos") ||
                        arithmetic_example.contains("sin") ||
                        arithmetic_example.contains("sqrt"))
                    continue;
                else
                    return false;
            }
        return true;
    }

    // проверка на заполнение поля
    private boolean meseg_error_emptyTextField(String arithmetic_example){
        if (arithmetic_example.length() == 0) return false;
        return true;
    }

    // Language translation
    private void RussianL(){
        nameProgram.setText("Builder Graph");
        meseg_one.setText("<html>Для правильной записи гиперболы<br>" +
                "запишите все её части в скобки, пример:<br>" +
                "y = (k) / (x)<br><br>" +
                "Квадратные уравнения<br>проcьба записывать в виде:<br>ax^2 ± bx ± c<br><br>" +
                "Знак степни записывается как ** либо ^<br>" +
                "корень sqrt(x ± ....)</html>");
        meseg_error_symbolsFilter.setText("Неверно раставлены скобки");
        meseg_error_brackets.setText("Введены недоступные символы");
        meseg_error_emptyTextField.setText("Введите функцию");
        Enter_BuildGraph.setText("Пострить график");
    }
    private void EnglishL(){
        nameProgram.setText("Builder Graph");
        meseg_one.setText("<html>To correctly write a hyperbola<br>" +
                "write all its parts in brackets, for example:<br>" +
                "y = (k) / (x)<br><br>" +
                "Quadratic equations<br>please write in the form:<br>ax^2 ± bx ± c<br><br>" +
                "The degree sign is written as ** or ^<br>" +
                "root sqrt(x ± ....)</html>");
        meseg_error_symbolsFilter.setText("Parentheses are incorrectly placed");
        meseg_error_brackets.setText("Invalid characters entered");
        meseg_error_emptyTextField.setText("Enter a function");
        Enter_BuildGraph.setText("Build a graph");
    }
    private void EspanolL(){
        nameProgram.setText("Builder Graph");
        meseg_one.setText("<html>Para escribir correctamente una hipérbola, escribe<br>" +
                "todas sus partes entre paréntesis, por ejemplo:<br>" +
                "y = (k) / (x)<br><br>" +
                "Ecuaciones cuadráticas<br>por favor escribe en la forma:<br>ax^2 ± bx ± c<br><br>" +
                "El signo de grado se escribe como ** o ^<br>" +
                "raíz sqrt(x ± ....)</html>");
        meseg_error_symbolsFilter.setText("Los paréntesis están mal colocados");
        meseg_error_brackets.setText("Se ingresaron caracteres no válidos");
        meseg_error_emptyTextField.setText("Introduzca una función");
        Enter_BuildGraph.setText("Construye un gráfico");
    }
}