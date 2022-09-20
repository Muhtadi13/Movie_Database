package sample;

import javafx.application.Platform;
import server.Movie;
import util.*;

import java.io.IOException;
import java.util.List;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final Main main;

    public ReadThread(Main main) {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        System.out.println(loginDTO.getUserName());
                        System.out.println(loginDTO.isStatus());
                        // the following is necessary to update JavaFX UI components from user created threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loginDTO.isStatus()) {
                                    try {
                                        main.showProdComPage(loginDTO.getUserName());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    main.showAlert();
                                }

                            }
                        });
                    }
                    else if(o instanceof InfoUtil)
                    {
                        InfoUtil iu=(InfoUtil) o;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if(iu.getInstruction().equals("totalprofit"))
                                        main.showProfitScene((Long) iu.getInfo());
                                    else
                                    main.showTransferScene((List<Movie>) iu.getInfo());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    else if(o instanceof TranferUtil)
                    {
                        TranferUtil tu=(TranferUtil) o;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    main.showTransferScene((List<Movie>) tu.getMovie());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    else if(o instanceof AdderUtil)
                    {
                        AdderUtil au=(AdderUtil) o;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    main.showTransferScene((List<Movie>) au.getData());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                main.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



