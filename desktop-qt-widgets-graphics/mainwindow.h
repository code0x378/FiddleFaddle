#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include "game.h"

#include <QMainWindow>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);

    ~MainWindow();

protected:
    void resizeEvent(QResizeEvent *evt);

private slots:
    void nextQuestion();

    void resetGame();

    void showSettings();

    void checkAnswer();

private:
    Ui::MainWindow *ui;

    Game *game = new Game();

    void displayQuestion(Question *q);

    void displayResult(bool result);

    void updateScore();

    void initGui();
};

#endif // MAINWINDOW_H
