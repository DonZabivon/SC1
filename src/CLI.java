import command.Command;
import command.account.AccountCommandFactoryConsole;
import command.category.CategoryCommandFactoryConsole;
import command.operation.OperationCommandFactoryConsole;
import dao.BankAccountDAO;
import dao.CategoryDAO;
import dao.OperationDAO;
import dao.inmemory.InMemoryBankAccountDAO;
import dao.inmemory.InMemoryCategoryDAO;
import dao.inmemory.InMemoryOperationDAO;
import data.CSVExporter;
import data.CSVImporter;
import decorator.StatCommand;
import service.BankAccountService;
import service.CategoryService;
import service.OperationService;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CLI {
    private Scanner scanner = new Scanner(System.in);

    private BankAccountService bankAccountService;
    private CategoryService categoryService;
    private OperationService operationService;

    private AccountCommandFactoryConsole accountCommandFactory;
    private CategoryCommandFactoryConsole categoryCommandFactory;
    private OperationCommandFactoryConsole operationCommandFactory;

    private boolean showExecutionTime = true;

    public CLI() {
        BankAccountDAO bankAccountDAO = new InMemoryBankAccountDAO();
        bankAccountService = new BankAccountService(bankAccountDAO);

        CategoryDAO categoryDAO = new InMemoryCategoryDAO();
        categoryService = new CategoryService(categoryDAO);

        OperationDAO operationDAO = new InMemoryOperationDAO();
        operationService = new OperationService(operationDAO);

        accountCommandFactory = new AccountCommandFactoryConsole(scanner, bankAccountService);
        categoryCommandFactory = new CategoryCommandFactoryConsole(scanner, categoryService);
        operationCommandFactory = new OperationCommandFactoryConsole(scanner, bankAccountService, categoryService, operationService);
    }

    public void run() {
        System.out.printf("Добро пожаловать в систему 'Учет финансов'!%n");

        boolean exit = false;
        while (!exit) {
            int choice = mainMenu();
            switch (choice) {
                case 1:
                    dealWithAccounts();
                    break;
                case 2:
                    dealWithCategories();
                    break;
                case 3:
                    dealWithOperations();
                    break;
                case 4:
                    dealWithAnalysis();
                    break;
                case 5:
                    dealWithImport();
                    break;
                case 6:
                    dealWithExport();
                    break;
                case 7:
                    dealWithOptions();
                    break;
                case 0:
                    exit = true;
                    break;
            }
        }

        System.out.printf("%nДо свидания!%n");
    }

    private void dealWithAccounts() {
        System.out.printf("%nМодуль 'Работа со счетами'%n");

        boolean exit = false;
        while (!exit) {
            int choice = accountMenu();
            Command command = null;
            switch (choice) {
                case 1:
                    System.out.printf("%nСоздание счета:%n");
                    command = accountCommandFactory.newCreateAccountCommand();
                    break;
                case 2:
                    System.out.printf("%nУдаление счета:%n");
                    command = accountCommandFactory.newDeleteAccountCommand();
                    break;
                case 3:
                    System.out.printf("%nРедактирование счета:%n");
                    command = accountCommandFactory.newUpdateAccountCommand();
                    break;
                case 4:
                    System.out.printf("%nПоиск счета:%n");
                    command = accountCommandFactory.newFindAccountCommand();
                    break;
                case 5:
                    System.out.printf("%nСписок счетов:%n");
                    command = accountCommandFactory.newListAccountCommand();
                    break;
                case 0:
                    exit = true;
                    break;
            }
            if (command != null) {
                // Оборачиваем в декоратор
                if (showExecutionTime) {
                    command = new StatCommand(command);
                }
                try {
                    command.execute();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    private void dealWithCategories() {
        System.out.printf("%nМодуль 'Работа с категориями'%n");

        boolean exit = false;
        while (!exit) {
            int choice = categoryMenu();
            Command command = null;
            switch (choice) {
                case 1:
                    System.out.printf("%nСоздание категории:%n");
                    command = categoryCommandFactory.newCreateCategoryCommand();
                    break;
                case 2:
                    System.out.printf("%nУдаление категории:%n");
                    command = categoryCommandFactory.newDeleteCategoryCommand();
                    break;
                case 3:
                    System.out.printf("%nРедактирование категории:%n");
                    command = categoryCommandFactory.newUpdateCategoryCommand();
                    break;
                case 4:
                    System.out.printf("%nПоиск категории:%n");
                    command = categoryCommandFactory.newFindCategoryCommand();
                    break;
                case 5:
                    System.out.printf("%nСписок категорий:%n");
                    command = categoryCommandFactory.newListCategoryCommand();
                    break;
                case 0:
                    exit = true;
                    break;
            }
            if (command != null) {
                // Оборачиваем в декоратор
                if (showExecutionTime) {
                    command = new StatCommand(command);
                }
                try {
                    command.execute();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    private void dealWithOperations() {
        System.out.printf("%nМодуль 'Работа с операциями'%n");

        boolean exit = false;
        while (!exit) {
            int choice = operationMenu();
            Command command = null;
            switch (choice) {
                case 1:
                    System.out.printf("%nСоздание операции:%n");
                    command = operationCommandFactory.newCreateOperationCommand();
                    break;
                case 2:
                    System.out.printf("%nУдаление операции:%n");
                    command = operationCommandFactory.newDeleteOperationCommand();
                    break;
                case 3:
                    System.out.printf("%nРедактирование операции:%n");
                    command = operationCommandFactory.newUpdateOperationCommand();
                    break;
                case 4:
                    System.out.printf("%nПоиск операции:%n");
                    command = operationCommandFactory.newFindOperationCommand();
                    break;
                case 5:
                    System.out.printf("%nСписок операций:%n");
                    command = operationCommandFactory.newListOperationCommand();
                    break;
                case 0:
                    exit = true;
                    break;
            }
            if (command != null) {
                // Оборачиваем в декоратор
                if (showExecutionTime) {
                    command = new StatCommand(command);
                }
                try {
                    command.execute();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    private void dealWithAnalysis() {
        System.out.printf("%nМодуль 'Анализ данных'%n");
        System.out.printf("В процессе разработки...%n");
    }

    private void dealWithImport() {
        System.out.printf("%nМодуль 'Импорт данных'%n");
        //System.out.printf("В процессе разработки...%n");

        CSVImporter csvImporter = new CSVImporter(bankAccountService, categoryService, operationService);
        try {
            csvImporter.importData(new File("accounts.csv"),
                    new File("categories.csv"),
                    new File("operations.csv"));
            System.out.println("Данные загружены");
        } catch (IOException e) {
            System.out.println("Ошибка при импорте данных");
        }
    }

    private void dealWithExport() {
        System.out.printf("%nМодуль 'Экспорт данных'%n");
        //System.out.printf("В процессе разработки...%n");

        CSVExporter csvExporter = new CSVExporter(bankAccountService, categoryService, operationService);
        try {
            csvExporter.exportData(new File("accounts.csv"),
                    new File("categories.csv"),
                    new File("operations.csv"));
            System.out.println("Данные сохранены");
        } catch (IOException e) {
            System.out.println("Ошибка при экспорте данных");
        }
    }

    private void dealWithOptions() {
        System.out.printf("%nМодуль 'Настройки'%n");
        System.out.printf("В процессе разработки...%n");
    }

    private int mainMenu() {
        System.out.printf("%nГлавное меню:%n");
        System.out.println("  (1) Счета");
        System.out.println("  (2) Категории");
        System.out.println("  (3) Операции");
        System.out.println("  (4) Аналитика");
        System.out.println("  (5) Импорт данных");
        System.out.println("  (6) Экспорт данных");
        System.out.println("  (7) Настройки");
        System.out.println("  (0) Выход");
        System.out.print("Ваш выбор: ");
        int choice;
        while (true) {
            try {
                choice = inputNumber();
                if (choice < 0 || choice > 7) {
                    throw new InputMismatchException();
                }
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Пожалуйста, введите корректную опцию!");
            }
        }
    }

    private int accountMenu() {
        System.out.printf("%nМеню работы со счетами:%n");
        System.out.println("  (1) Создать");
        System.out.println("  (2) Удалить");
        System.out.println("  (3) Редактировать");
        System.out.println("  (4) Найти");
        System.out.println("  (5) Список");
        System.out.println("  (0) Вернуться в предыдущее меню");
        System.out.print("Ваш выбор: ");
        int choice;
        while (true) {
            try {
                choice = inputNumber();
                if (choice < 0 || choice > 5) {
                    throw new InputMismatchException();
                }
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Пожалуйста, введите корректную опцию!");
            }
        }
    }

    private int categoryMenu() {
        System.out.printf("%nМеню работы с категориями:%n");
        System.out.println("  (1) Создать");
        System.out.println("  (2) Удалить");
        System.out.println("  (3) Редактировать");
        System.out.println("  (4) Найти");
        System.out.println("  (5) Список");
        System.out.println("  (0) Вернуться в предыдущее меню");
        System.out.print("Ваш выбор: ");
        int choice;
        while (true) {
            try {
                choice = inputNumber();
                if (choice < 0 || choice > 5) {
                    throw new InputMismatchException();
                }
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Пожалуйста, введите корректную опцию!");
            }
        }
    }

    private int operationMenu() {
        System.out.printf("%nМеню работы с операциями:%n");
        System.out.println("  (1) Создать");
        System.out.println("  (2) Удалить");
        System.out.println("  (3) Редактировать");
        System.out.println("  (4) Найти");
        System.out.println("  (5) Список");
        System.out.println("  (0) Вернуться в предыдущее меню");
        System.out.print("Ваш выбор: ");
        int choice;
        while (true) {
            try {
                choice = inputNumber();
                if (choice < 0 || choice > 5) {
                    throw new InputMismatchException();
                }
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Пожалуйста, введите корректную опцию!");
            }
        }
    }

    private int analysisMenu() {
        return 0;
    }

    private int importMenu() {
        return 0;
    }

    private int exportMenu() {
        return 0;
    }

    private int inputNumber() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new InputMismatchException();
        }
    }

    public static void main(String[] args) {
        CLI cli = new CLI();
        cli.run();
    }
}
