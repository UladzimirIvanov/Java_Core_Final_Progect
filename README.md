# Java Core Final Progect
### Описание:

Данный проект позволяет осуществлять денежный перевод с одного счета на другой

### Входные данные:
**src\Files\Accounts.txt** - файл, содержащий в себе информацию обо всех доступных счетах и суммах на них. Автоматически изменяется, по мере выполнения программы.

**src\Files\Report.txt** - лог-файл, содержащий в себе информацию обо всех совершённых операциях, с указанием даты и времени операции, названием входящего файла и результате операции. Автоматически дополняется по мере выполнения программы.

**src\Files\Input\\** - каталог, в котором находятся файлы ожидающие обработки.

**src\Files\Archive\\** - каталог, в который перемещаются файлы, которые прошли обработку. К именам файлов присваевается Дата и время их обработки.

### Принцип работы:
После запуска программа ожидает пользовательский ввод с клавиатуры.

Если пользователь выбрал парсинг фалов, то все файлы лежащие в каталоге src\Files\Input\ и имеющие расширение .txt будут проверены на соблюдение определённых условий и обработаны. При успешном выполнении, будет осуществлён перевод с одного счёта на другой, информация о переводе запишется в лог-файл. В случае, если не будет найден счёт или же на счету не будет достаточно средств для перевода, перевод не осуществится, данные об ошибке перевода будут записаны в лог-файл.

Если пользователь выбрал просмотр отчёта, то вся находящаяся информация в лог-файле будет выведена в консоль.

Если пользователь выбрал информацию о счетах, то вся информация из файла *Accounts.txt* будет выведена в консоль.

### Дополнительные сведения
При необходимости, можно пополнять файл *Accounts.txt* новыми счетами и значениями для каждого счёта. Формат данных должен быть ХХХХХ-ХХХХХ | ХХХ

Так же можно добавлять новые файлы src\Files\Input\ с указаниями с какого счёта будет перевод, на какой счёт, какая сумма. Формат данных должен быть ХХХХХ-ХХХХХ | ХХХХХ-ХХХХХ | ХХХ
