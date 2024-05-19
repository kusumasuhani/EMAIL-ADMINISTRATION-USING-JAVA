import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class EmailAdminApp2 {
    private JFrame frame;
    private JPanel panel;
    private Map<String, String> users;
    private JLabel userDetailsLabel;
    private String currentUser;
    private JButton viewDetailsButton;

    public EmailAdminApp2() {
        frame = new JFrame("Email Administration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        panel = new JPanel(new FlowLayout());
        frame.add(panel);

        users = new HashMap<>();

        JButton createButton = new JButton("Create");
        panel.add(createButton);

        JButton loginButton = new JButton("Login");
        panel.add(loginButton);

        viewDetailsButton = new JButton("View Details");
        panel.add(viewDetailsButton);
        viewDetailsButton.setEnabled(false);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCreateDialog();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginDialog();
            }
        });

        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("View Details button clicked");
                if (currentUser != null) {
                    System.out.println("Showing user details: " + currentUser);
                    showUserDetails();
                } else {
                    System.out.println("No user is currently logged in.");
                }
            }
        });

        frame.setVisible(true);
    }

    private void showCreateDialog() {
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField confirmPasswordField = new JPasswordField();
        JTextField mobileNumberField = new JTextField();

        Object[] fields = {
                "First Name:", firstNameField,
                "Last Name:", lastNameField,
                "Email:", emailField,
                "Password:", passwordField,
                "Confirm Password:", confirmPasswordField,
                "Mobile Number:", mobileNumberField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Create Account", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            // Validate if the email contains '@'
            if (!email.contains("@")) {
                JOptionPane.showMessageDialog(frame, "Invalid email format. Please enter a valid email address.");
            } else if (users.containsKey(email)) {
                JOptionPane.showMessageDialog(frame, "Email already in use. Please choose a different email.");
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match. Please try again.");
            } else {
                users.put(email, password);
                JOptionPane.showMessageDialog(frame, "Account created successfully.");
            }
        }
    }

    private void showLoginDialog() {
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        Object[] fields = {
                "Email:", emailField,
                "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Login", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (users.containsKey(email) && users.get(email).equals(password)) {
              
                currentUser = email;
                viewDetailsButton.setEnabled(true);
                JOptionPane.showMessageDialog(frame, "Login successful.");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid email or password.");
            }
        }
    }

    private void showUserDetails() {

        if (currentUser != null) {
            String userDetailsMessage = "User Details: " + currentUser;
            JOptionPane.showMessageDialog(frame, userDetailsMessage, "User Details", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            public void run() {
                new EmailAdminApp2();
            }
        });
    }
}
