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
    connect(ui->actionQuit, SIGNAL(triggered()), this, SLOT(close()));
    connect(ui->actionAbout, SIGNAL(triggered()), this, SLOT(showAbout()));

    connect(ui->nextButton, SIGNAL(clicked()), this, SLOT(nextQuestion()));
    connect(ui->resetButton, SIGNAL(clicked()), this, SLOT(resetGame()));
    connect(ui->answerOneRadioButton, SIGNAL(clicked()), this, SLOT(checkAnswer()));
    connect(ui->answerTwoRadioButton, SIGNAL(clicked()), this, SLOT(checkAnswer()));
    connect(ui->answerThreeRadioButton, SIGNAL(clicked()), this, SLOT(checkAnswer()));
    connect(ui->answerFourRadioButton, SIGNAL(clicked()), this, SLOT(checkAnswer()));
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

void MainWindow::showAbout()
{
    QMessageBox::about(this, "About Fiddle Faddle",
                       QString("Fiddle Faddle\n\nCopyright 2017 FattySmurff"));
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
