import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tycho on 20-6-2017.
 */
public class Scorebord extends JPanel {
    private boolean status = false;
    private double tijdSec = 0;
    private int tijdMin = 0;
    private double rekenTijd;
    private Scheidsrechter Scheids;
    private Timer timer;
    private Font leTycho;
    private Font leTychoGroot;
    private Font leTychoKlein;
    private TimerTask taak;
    private DecimalFormat f;
    private JLabel schermTijd, teamScores, kaartenScore;
    private JButton startKnop, pauzeKnop, verderKnop, resetKnop, team1Knop, team2Knop, team1RoodKnop, team2RoodKnop, team1GeelKnop, team2GeelKnop;
    private JPanel knoppenPaneel, scheidsPaneel, scheidsKaartPaneel;

    public Scorebord() {
        setLayout(new GridLayout(6, 0));

        leTycho = new Font("Arial", Font.BOLD, 15);
        leTychoGroot = new Font("Arial", Font.BOLD, 20);
        leTychoKlein = new Font("Arial", Font.BOLD, 10);
        Scheids = new Scheidsrechter();
        schermTijd = new JLabel(Integer.toString(getTijdMin()) + " minuten en " + Double.toString(getTijdSec()) + " seconden");
        teamScores = new JLabel("Team 1: " + Scheids.getTeam1() + " Team 2: " + Scheids.getTeam2());
        kaartenScore = new JLabel("R1: " + Scheids.getRodeKaartTeam1() + "G1: " + Scheids.getGeleKaartTeam1() + "R2: " + Scheids.getRodekaartTeam2() + "G2: " + Scheids.getGeleKaartTeam2());
        startKnop = new JButton("Start");
        pauzeKnop = new JButton("Pauzeer");
        verderKnop = new JButton("Verder gaan");
        resetKnop = new JButton("Reset");
        team1Knop = new JButton("Doelpunt team 1");
        team2Knop = new JButton("Doelpunt team 2");
        team1RoodKnop = new JButton("R1");
        team2RoodKnop = new JButton("R2");
        team1GeelKnop = new JButton("G1");
        team2GeelKnop = new JButton("G2");
        scheidsPaneel = new JPanel(new GridLayout(0, 2));
        knoppenPaneel = new JPanel(new GridLayout(0, 4));
        scheidsKaartPaneel = new JPanel(new GridLayout(0, 4));

        startKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });
        pauzeKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauzeTimer();
            }
        });
        verderKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verderTimer();
            }
        });
        resetKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTimer();
            }
        });
        team1Knop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int goals = Scheids.getTeam1();
                Scheids.setTeam1(goals + 1);
                teamScores.setText("Team 1: " + Scheids.getTeam1() + " Team 2: " + Scheids.getTeam2());
            }
        });
        team2Knop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int goals = Scheids.getTeam2();
                Scheids.setTeam2(goals + 1);
                teamScores.setText("Team 1: " + Scheids.getTeam1() + " Team 2: " + Scheids.getTeam2());
            }
        });
        team1RoodKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rood = Scheids.getRodeKaartTeam1();
                Scheids.setRodeKaartTeam1(rood + 1);
                kaartenScore.setText("R1: " + Scheids.getRodeKaartTeam1() + "G1: " + Scheids.getGeleKaartTeam1() + "R2: " + Scheids.getRodekaartTeam2() + "G2: " + Scheids.getGeleKaartTeam2());
            }
        });
        team2RoodKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rood = Scheids.getRodekaartTeam2();
                Scheids.setRodekaartTeam2(rood + 1);
                kaartenScore.setText("R1: " + Scheids.getRodeKaartTeam1() + "G1: " + Scheids.getGeleKaartTeam1() + "R2: " + Scheids.getRodekaartTeam2() + "G2: " + Scheids.getGeleKaartTeam2());
            }
        });
        team1GeelKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int geel = Scheids.getGeleKaartTeam1();
                Scheids.setGeleKaartTeam1(geel + 1);
                kaartenScore.setText("R1: " + Scheids.getRodeKaartTeam1() + "G1: " + Scheids.getGeleKaartTeam1() + "R2: " + Scheids.getRodekaartTeam2() + "G2: " + Scheids.getGeleKaartTeam2());
            }
        });
        team2GeelKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int geel = Scheids.getGeleKaartTeam2();
                Scheids.setGeleKaartTeam2(geel + 1);
                kaartenScore.setText("R1: " + Scheids.getRodeKaartTeam1() + "G1: " + Scheids.getGeleKaartTeam1() + "R2: " + Scheids.getRodekaartTeam2() + "G2: " + Scheids.getGeleKaartTeam2());
            }
        });

        startKnop.setFont(leTycho);
        pauzeKnop.setFont(leTycho);
        pauzeKnop.setEnabled(false);
        verderKnop.setEnabled(false);
        verderKnop.setFont(leTycho);
        resetKnop.setFont(leTycho);
        team1Knop.setFont(leTychoKlein);
        team1Knop.setEnabled(false);
        team1GeelKnop.setEnabled(false);
        team2GeelKnop.setEnabled(false);
        team1RoodKnop.setEnabled(false);
        team2RoodKnop.setEnabled(false);
        team2Knop.setEnabled(false);
        team2Knop.setFont(leTychoKlein);
        schermTijd.setFont(leTychoGroot);
        teamScores.setFont(leTychoGroot);
        kaartenScore.setFont(leTycho);

        add(schermTijd);
        add(teamScores);
        add(kaartenScore);
        add(scheidsPaneel);
        add(scheidsKaartPaneel);
        add(knoppenPaneel);

        setBorder(BorderFactory.createEmptyBorder(
                20, //top
                20,     //left
                20, //bottom
                20));   //right

        scheidsPaneel.add(team1Knop);
        scheidsPaneel.add(team2Knop);
        scheidsKaartPaneel.add(team1RoodKnop);
        scheidsKaartPaneel.add(team1GeelKnop);
        scheidsKaartPaneel.add(team2RoodKnop);
        scheidsKaartPaneel.add(team2GeelKnop);
        knoppenPaneel.add(startKnop);
        knoppenPaneel.add(pauzeKnop);
        knoppenPaneel.add(verderKnop);
        knoppenPaneel.add(resetKnop);


    }

    public void startTimer() {
        Timer timer = new Timer();
        setTimer(timer);
        setTimerTask(taak = new TimerTask() {
            @Override
            public void run() {
                rekenTijd = getTijdSec() + 0.1;
                setTijdSec(Math.round(rekenTijd * 100.0) / 100.0);
                schermTijd.setText(Integer.toString(getTijdMin()) + " minuten en " + Double.toString(getTijdSec()) + " seconden");
                System.out.println(Integer.toString(getTijdMin()) + " minuten en " + Double.toString(getTijdSec()) + " seconden");
                if (getTijdSec() == 60) {
                    setTijdMin(getTijdMin() + 1);
                    setTijdSec(0);
                }
                if (getTijdMin() == 90) {
                    schermTijd.setText(Integer.toString(getTijdMin()) + " minuten en 0 seconden");
                    getTimer().cancel();
                    setStatus(false);
                } else {
                    System.out.println("AAN");
                }
            }
        });
        setStatus(true);
        f = new DecimalFormat("##.00");
        getTimer().schedule(getTimerTask(), 0, 100);
        startKnop.setEnabled(false);
        pauzeKnop.setEnabled(true);
        verderKnop.setEnabled(true);
        team1Knop.setEnabled(true);
        team2Knop.setEnabled(true);
        team1GeelKnop.setEnabled(true);
        team2GeelKnop.setEnabled(true);
        team1RoodKnop.setEnabled(true);
        team2RoodKnop.setEnabled(true);
    }

    public void pauzeTimer() {
        if (getStatus()) {
            getTimer().cancel();
            setStatus(false);
            team1Knop.setEnabled(false);
            team2Knop.setEnabled(false);
            team1GeelKnop.setEnabled(false);
            team2GeelKnop.setEnabled(false);
            team1RoodKnop.setEnabled(false);
            team2RoodKnop.setEnabled(false);
        } else {
            System.out.println(getStatus());
        }
    }

    public void verderTimer() {
        Timer timer2 = new Timer();
        setTimer(timer2);
        if (!getStatus()) {
            setTimerTask(taak = new TimerTask() {
                @Override
                public void run() {
                    rekenTijd = getTijdSec() + 0.1;
                    setTijdSec(Math.round(rekenTijd * 100.0) / 100.0);
                    schermTijd.setText(Integer.toString(getTijdMin()) + " minuten en " + Double.toString(getTijdSec()) + " seconden");
                    System.out.println(Integer.toString(getTijdMin()) + " minuten en " + Double.toString(getTijdSec()) + " seconden");
                    if (getTijdSec() == 60.0) {
                        setTijdMin(getTijdMin() + 1);
                        setTijdSec(0);
                    }
                    if (getTijdMin() == 90) {
                        schermTijd.setText(Integer.toString(getTijdMin()) + " minuten en 0 seconden");
                        getTimer().cancel();
                        setStatus(false);

                    } else {
                        System.out.println("AAN");
                    }
                }
            });
            Timer timer = getTimer();
            timer.schedule(getTimerTask(), 0, 100);
            setStatus(true);
            team1Knop.setEnabled(true);
            team2Knop.setEnabled(true);
            team1GeelKnop.setEnabled(true);
            team2GeelKnop.setEnabled(true);
            team1RoodKnop.setEnabled(true);
            team2RoodKnop.setEnabled(true);
        } else {
            System.out.println("Al aangezet");
        }
    }

    public void resetTimer() {
        pauzeTimer();
        setTijdSec(0);
        setTijdMin(0);
        startKnop.setEnabled(true);
        pauzeKnop.setEnabled(false);
        verderKnop.setEnabled(false);
        team1Knop.setEnabled(false);
        team2Knop.setEnabled(false);
        team1GeelKnop.setEnabled(false);
        team2GeelKnop.setEnabled(false);
        team1RoodKnop.setEnabled(false);
        team2RoodKnop.setEnabled(false);
        Scheids.setTeam1(0);
        Scheids.setTeam2(0);
        Scheids.setGeleKaartTeam1(0);
        Scheids.setGeleKaartTeam2(0);
        Scheids.setRodeKaartTeam1(0);
        Scheids.setRodekaartTeam2(0);

        schermTijd.setText(Integer.toString(getTijdMin()) + " minuten en " + getTijdSec() + " seconden");
        teamScores.setText("Team 1: " + Scheids.getTeam1() + " Team 2: " + Scheids.getTeam2());
        kaartenScore.setText("R1: " + Scheids.getRodeKaartTeam1() + "G1: " + Scheids.getGeleKaartTeam1() + "R2: " + Scheids.getRodekaartTeam2() + "G2: " + Scheids.getGeleKaartTeam2());

    }

    public double getTijdSec() {
        return this.tijdSec;
    }

    public int getTijdMin() {
        return this.tijdMin;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public boolean getStatus() {
        return this.status;
    }

    public TimerTask getTimerTask() {
        return this.taak;
    }

    public void setTijdSec(double sec) {
        this.tijdSec = sec;
    }

    public void setTijdMin(int min) {
        this.tijdMin = min;
    }

    public void setTimer(Timer t) {
        this.timer = t;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTimerTask(TimerTask task) {
        this.taak = task;
    }
}
