#include "mainwindow.h"
#include "ui_mainwindow.h"

#include <QCheckBox>
#include <QDebug>
#include <QFontDatabase>
#include <QMessageBox>

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    initGui();
    resetGame();
}

void MainWindow::initGui()
{
    QFontDatabase::addApplicationFont(":/resources/assets/fonts/soft-marshmallow.otf");
    QFont normalFont = QFont("Soft Marshmallow", 16, 1);
    QFont largeFont = QFont("Soft Marshmallow", 20, 1);

    QIcon resetImg(":/resources/assets/img/button-reset.png");
    ui->resetButton->setIcon(resetImg);
    ui->resetButton->setIconSize(QSize(109, 52));
    ui->resetButton->setFlat(true);
    ui->resetButton->setText("");
    ui->resetButton->setAutoFillBackground(false);

    QIcon nextImg(":/resources/assets/img/button-next.png");
    ui->nextButton->setIcon(nextImg);
    ui->nextButton->setIconSize(QSize(75, 52));
    ui->nextButton->setFlat(true);
    ui->nextButton->setText("");
    ui->nextButton->setAutoFillBackground(false);

    QIcon settingsImg(":/resources/assets/img/button-settings.png");
    ui->settingsButton->setIcon(settingsImg);
    ui->settingsButton->setIconSize(QSize(52, 52));
    ui->settingsButton->setFlat(true);
    ui->settingsButton->setText("");
    ui->settingsButton->setAutoFillBackground(false);

    QPixmap bkgnd(":/resources/assets/img/title.png");
    ui->titleLabel->setPixmap(bkgnd);

    ui->scoreLabel->setFont(largeFont);
    ui->scoreLabel->setStyleSheet("color: #d62fcf");
    ui->levelNumberLabel->setFont(largeFont);
    ui->levelNumberLabel->setStyleSheet("color: #d62fcf");
    ui->questionLabel->setFont(normalFont);
    ui->answerGroupBox->setFont(normalFont);

    connect(ui->nextButton, SIGNAL(clicked()), this, SLOT(nextQuestion()));
    connect(ui->resetButton, SIGNAL(clicked()), this, SLOT(resetGame()));
    connect(ui->settingsButton, SIGNAL(clicked()), this, SLOT(showSettings()));
    connect(ui->answerOneRadioButton, SIGNAL(clicked()), this, SLOT(checkAnswer()));
    connect(ui->answerTwoRadioButton, SIGNAL(clicked()), this, SLOT(checkAnswer()));
    connect(ui->answerThreeRadioButton, SIGNAL(clicked()), this, SLOT(checkAnswer()));
    connect(ui->answerFourRadioButton, SIGNAL(clicked()), this, SLOT(checkAnswer()));
}

void MainWindow::resizeEvent(QResizeEvent *evt)
{
    QPixmap bkgnd(":/resources/assets/img/background.png");
    bkgnd = bkgnd.scaled(size(), Qt::IgnoreAspectRatio);
    QPalette p = palette();
    p.setBrush(QPalette::Background, bkgnd);
    setPalette(p);

    QMainWindow::resizeEvent(evt);
}

void MainWindow::nextQuestion()
{
    updateScore();
    displayQuestion(game->getNextQuestion());
}

void MainWindow::showSettings()
{
    QMessageBox::information(this, "Info", "Implement Settings", QMessageBox::Ok);
}

void MainWindow::resetGame()
{
    ui->levelNumberLabel->setText(QString("Level: %1").arg(game->getLevel()));
    delete game;
    game = new Game();
    updateScore();
    displayQuestion(game->getQuestion());
}

void MainWindow::displayQuestion(Question *q)
{
    ui->answerGroupBox->setEnabled(true);
    QList<QRadioButton *> radioButtons = ui->answerGroupBox->findChildren<QRadioButton *>();
    for (QRadioButton *radio : radioButtons) {
        radio->setAutoExclusive(false);
        radio->setChecked(false);
        radio->setAutoExclusive(true);
    }

    ui->answerGroupBox->setTitle("Select Answer");
    ui->questionLabel->setText(q->getQuestion());
    ui->answerOneRadioButton->setText(q->getAnswers().at(0));
    ui->answerTwoRadioButton->setText(q->getAnswers().at(1));
    ui->answerThreeRadioButton->setText(q->getAnswers().at(2));
    ui->answerFourRadioButton->setText(q->getAnswers().at(3));
}

void MainWindow::updateScore()
{
    ui->scoreLabel->setText(QString("Score: %1 / %2").arg(game->getCorrect()).arg(game->getTotal()));
}

/**
 * @brief MainWindow::checkAnswer
 *
 * @Fixme - Lame implementation
 */
void MainWindow::checkAnswer()
{
    QObject *senderObj = sender();
    QString senderObjName = senderObj->objectName();

    if (senderObjName == "answerOneRadioButton") {
        displayResult(game->isCorrectAnswer(0));
    } else if (senderObjName == "answerTwoRadioButton") {
        displayResult(game->isCorrectAnswer(1));
    } else if (senderObjName == "answerThreeRadioButton") {
        displayResult(game->isCorrectAnswer(2));
    } else if (senderObjName == "answerFourRadioButton") {
        displayResult(game->isCorrectAnswer(3));
    }
}

void MainWindow::displayResult(bool result)
{
    if (result) {
        ui->answerGroupBox->setTitle("Correct!");
    } else {
        ui->answerGroupBox->setTitle("Wrong!");
    }
    updateScore();
    ui->answerGroupBox->setEnabled(false);
}

MainWindow::~MainWindow()
{
    delete ui;
}
