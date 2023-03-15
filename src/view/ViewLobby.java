package view;

import connection.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewLobby {
    private final JTextField usernameField;
    private final JTextField ipAddressField;
    private boolean isConnected;
    private JList<String> playerList;
    private final JTextArea chatArea;
    private JTextField chatField;
    private final JTextField portField;
    private JButton connectButton;
    private Connection connection;
    private String username;

    public ViewLobby() {
        JFrame jframe = new JFrame("Snake Lobby");
        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(10);
        JLabel ipAddressLabel = new JLabel("Server IP address:");
        ipAddressField = new JTextField("localhost",10);
        JLabel portLabel = new JLabel("Server port:"); // Nouveau label pour le port
        portField = new JTextField("8082",5); // Nouveau champ pour le port
        connectButton = new JButton("Connect");
        playerList = new JList<String>();
        chatArea = new JTextArea(10, 30);
        chatArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatField = new JTextField(30);

        isConnected = false;

        // Set layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel connectPanel = new JPanel(new GridLayout(4, 2, 5, 5)); // Ajout d'une rangée pour le port
        JPanel playerListPanel = new JPanel(new BorderLayout());
        JPanel chatPanel = new JPanel(new BorderLayout());

        connectPanel.add(usernameLabel);
        connectPanel.add(usernameField);
        connectPanel.add(ipAddressLabel);
        connectPanel.add(ipAddressField);
        connectPanel.add(portLabel); // Nouvelle rangée pour le port
        connectPanel.add(portField); // Nouveau champ pour le port
        connectPanel.add(connectButton);
        mainPanel.add(connectPanel, BorderLayout.WEST);

        playerListPanel.setBorder(BorderFactory.createTitledBorder("Players"));
        playerListPanel.add(new JScrollPane(playerList));
        mainPanel.add(playerListPanel, BorderLayout.CENTER);

        chatPanel.setBorder(BorderFactory.createTitledBorder("Chat"));
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);
        chatPanel.add(chatField, BorderLayout.SOUTH);
        mainPanel.add(chatPanel, BorderLayout.SOUTH);

        //Event listener
        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connect();
            }
        });

        chatField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(isConnected) {
                    String message = chatField.getText();
                    message = username + ": " + message;
                    connection.sendMessage(message);
                    addChatMessage(message);
                    chatField.setText("");
                }
            }
        });


        // Add main panel to frame
        jframe.setContentPane(mainPanel);

        // Set frame properties
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setVisible(true);
    }

    private void Connect(){
        if(!isConnected) {
            this.username = usernameField.getText();
            String ipAddress = ipAddressField.getText();
            int port = Integer.parseInt(portField.getText());

            this.connection = new Connection(ipAddress, port, username, this);
        }
    }

    public void addChatMessage(String message) {
        chatArea.append(message + "\n");
    }

    public void setConnected() {
        isConnected = true;
    }

    public static void main(String[] args) {
        ViewLobby lobby = new ViewLobby();
    }
}

19h00->19h15 x3 x4
19h20->19h35 x3 x4
19h40->19h55 x3 x4
20h00->20h15 x3 x4

48 joueurs

24 6 matchs

20h45-> 21h00 x3
21h10 -> 21h25 x3

        12 joueurs

21h35->21h50 x3

        3 -> en finale
        3 -> en demi-finale

22h00->22h15
22h30->22h45