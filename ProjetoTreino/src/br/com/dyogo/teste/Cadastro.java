package br.com.dyogo.teste;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;
import java.awt.event.ActionEvent;

public class Cadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtTelefone;

	int posAtual = 0;
	int ultPos = 0;
	boolean novo = true;

	JButton btnNovo = new JButton("Novo");
	JButton btnSalvar = new JButton("Salvar");
	JButton btnApagar = new JButton("Deletar");
	JButton btnAlterar = new JButton("Alterar");
	JButton btnUltimo = new JButton(">>");
	JButton btnProximo = new JButton(">");
	JButton btnAnterior = new JButton("<");
	JButton btnPrimeiro = new JButton("<<");

	Connection con = ConnectionFactory.getConection();
	Agenda agd = new Agenda();
	List<Pessoas> lpss = agd.getList();
	Pessoas pss = lpss.get(ultPos);
	private JTextField txtId;
	private final JButton btnCancelar = new JButton("Cancelar");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Cadastro frame = new Cadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 244);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ativarComponentes();

				txtNome.setText(" ");
				txtTelefone.setText(" ");

				posAtual += 1;
				ultPos = lpss.size() - 1;
			}
		});
		btnNovo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNovo.setBounds(315, 22, 89, 23);
		contentPane.add(btnNovo);
		btnUltimo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (ultPos >= 0) {
					posAtual = lpss.size() - 1;
					Pessoas pess = lpss.get(posAtual);
					txtId.setText(String.valueOf(pess.getId()));
					txtNome.setText(pess.getNome());
					txtTelefone.setText(pess.getTelefone());
				}
			}
		});
		btnUltimo.setBounds(223, 171, 61, 23);
		contentPane.add(btnUltimo);
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (ultPos >= 0 && posAtual < lpss.size() - 1) {
					posAtual += 1;

					Pessoas pess = lpss.get(posAtual);
					txtId.setText(String.valueOf(pess.getId()));
					txtNome.setText(pess.getNome());
					txtTelefone.setText(pess.getTelefone());
				}
			}
		});
		btnProximo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnProximo.setBounds(152, 171, 61, 23);
		contentPane.add(btnProximo);
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (ultPos >= 0 && posAtual > 0) {
					posAtual -= 1;
					Pessoas pess = lpss.get(posAtual);
					txtId.setText(String.valueOf(pess.getId()));
					txtNome.setText(pess.getNome());
					txtTelefone.setText(pess.getTelefone());
				}
			}
		});
		btnAnterior.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAnterior.setBounds(81, 171, 61, 23);
		contentPane.add(btnAnterior);

		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setBounds(81, 55, 171, 20);
		txtNome.setColumns(10);
		txtNome.setText(pss.getNome());
		contentPane.add(txtNome);

		txtTelefone = new JTextField();
		txtTelefone.setEditable(false);
		txtTelefone.setBounds(81, 86, 171, 20);
		txtTelefone.setText(pss.getTelefone());
		txtTelefone.setColumns(10);
		contentPane.add(txtTelefone);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setBounds(25, 56, 46, 14);
		contentPane.add(lblNome);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTelefone.setBounds(10, 87, 61, 14);
		contentPane.add(lblTelefone);
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ultPos >= 0) {
					posAtual = 0;
					Pessoas pess = lpss.get(posAtual);
					txtId.setText(String.valueOf(pess.getId()));
					txtNome.setText(pess.getNome());
					txtTelefone.setText(pess.getTelefone());
				}
			}
		});
		btnPrimeiro.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPrimeiro.setBounds(10, 171, 61, 23);
		contentPane.add(btnPrimeiro);

		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desativarComponentes();

				if (!txtNome.getText().trim().isEmpty()) {
					Pessoas pess = lpss.get(posAtual);
					pess.setId(Integer.parseInt(txtId.getText()));
					pess.setNome(txtNome.getText());
					pess.setTelefone(txtTelefone.getText());
					if (novo) {
						agd.adiconar(pess);
						JOptionPane.showMessageDialog(null, "contato salvo");
					} else {
						agd.alterar(pess);
						JOptionPane.showMessageDialog(null, "Contato alterado");
					}
				}
				JOptionPane.showMessageDialog(null, "Error! O nome esta em branco");
				desativarComponentes();
				posAtual = posAtual;
				Pessoas pess = lpss.get(posAtual);
				txtId.setText(String.valueOf(pess.getId()));
				txtNome.setText(pess.getNome());
				txtTelefone.setText(pess.getTelefone());
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSalvar.setBounds(315, 56, 89, 23);
		contentPane.add(btnSalvar);
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Pessoas pessoa = lpss.get(posAtual);
				lpss.remove(posAtual);
				agd.remove(pessoa);
				txtId.setText(String.valueOf(pessoa.getId()));
				txtNome.setText(pessoa.getNome());
				txtTelefone.setText(pessoa.getTelefone());

			}
		});
		btnApagar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnApagar.setBounds(315, 93, 89, 23);
		contentPane.add(btnApagar);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ativarComponentes();
				txtNome.setEditable(true);
				txtTelefone.setEditable(true);

				novo = false;
			}
		});
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAlterar.setBounds(315, 128, 89, 23);
		contentPane.add(btnAlterar);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblId.setBounds(31, 27, 28, 14);
		contentPane.add(lblId);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(81, 24, 86, 20);
		txtId.setText(String.valueOf(pss.getId()));
		txtId.setColumns(10);
		contentPane.add(txtId);
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desativarComponentes();
				posAtual = posAtual;
				Pessoas pess = lpss.get(posAtual);
				txtId.setText(String.valueOf(pess.getId()));
				txtNome.setText(pess.getNome());
				txtTelefone.setText(pess.getTelefone());

			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBounds(294, 172, 110, 23);

		contentPane.add(btnCancelar);

	}

	// Ativador de componentes;
	public void ativarComponentes() {
		// botoes de acao
		btnNovo.setEnabled(false);
		btnSalvar.setEnabled(true);
		btnApagar.setEnabled(false);
		btnAlterar.setEnabled(false);
		btnCancelar.setEnabled(true);
		// botoes de navegacao
		btnPrimeiro.setEnabled(false);
		btnProximo.setEnabled(false);
		btnAnterior.setEnabled(false);
		btnUltimo.setEnabled(false);
		// textos
//		txtId.setEditable(true);
		txtNome.setEditable(true);
		txtTelefone.setEditable(true);

	}

	// Desativador de componentes;
	public void desativarComponentes() {
		// botoes de acao
		btnNovo.setEnabled(true);
		btnSalvar.setEnabled(false);
		btnApagar.setEnabled(true);
		btnAlterar.setEnabled(true);
		btnCancelar.setEnabled(false);
		// btnOk.setEnabled(false);
		// botoes de navegacao
		btnPrimeiro.setEnabled(true);
		btnProximo.setEnabled(true);
		btnAnterior.setEnabled(true);
		btnUltimo.setEnabled(true);
		// textos
//		txtId.setEditable(false);
		txtNome.setEditable(false);
		txtTelefone.setEditable(false);

	}
}
