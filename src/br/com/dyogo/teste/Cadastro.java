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

	int posicaoAtual = 0;
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
	List<Pessoa> lista = agd.getList();
	Pessoa pss = lista.get(posicaoAtual);
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

				txtId.setText("");
				txtNome.setText(" ");
				txtTelefone.setText(" ");

				lista.get(posicaoAtual);
			}
		});
		btnNovo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNovo.setBounds(315, 22, 89, 23);
		contentPane.add(btnNovo);
		btnUltimo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				posicaoAtual = lista.size() - 1;
				Pessoa pess = lista.get(posicaoAtual);
				txtId.setText(String.valueOf(pess.getId()));
				txtNome.setText(pess.getNome());
				txtTelefone.setText(pess.getTelefone());

			}
		});
		btnUltimo.setBounds(223, 171, 61, 23);
		contentPane.add(btnUltimo);
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (posicaoAtual < lista.size() - 1) {
					posicaoAtual += 1;

					Pessoa pess = lista.get(posicaoAtual);
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

				if (posicaoAtual > 0) {
					posicaoAtual -= 1;
					Pessoa pess = lista.get(posicaoAtual);
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
				posicaoAtual = 0;
				Pessoa pess = lista.get(posicaoAtual);
				txtId.setText(String.valueOf(pess.getId()));
				txtNome.setText(pess.getNome());
				txtTelefone.setText(pess.getTelefone());

			}
		});
		btnPrimeiro.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPrimeiro.setBounds(10, 171, 61, 23);
		contentPane.add(btnPrimeiro);

		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desativarComponentes();

				Pessoa pess;
				if (!txtNome.getText().trim().isEmpty()) {

					if (novo) {

						pess = new Pessoa();
						pess.setNome(txtNome.getText());
						pess.setTelefone(txtTelefone.getText());
						agd.adiconar(pess);
						JOptionPane.showMessageDialog(null, "contato salvo");
						pess.setId(agd.getUltId());
						lista.add(pess);

						txtId.setText(String.valueOf(pess.getId()));
						txtNome.setText(pess.getNome());
						txtTelefone.setText(pess.getTelefone());

					} else {
						pess = lista.get(posicaoAtual);
						pess.setNome(txtNome.getText());
						pess.setTelefone(txtTelefone.getText());
						agd.alterar(pess);
						JOptionPane.showMessageDialog(null, "Contato alterado");

						txtId.setText(String.valueOf(pess.getId()));
						txtNome.setText(pess.getNome());
						txtTelefone.setText(pess.getTelefone());
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error! O nome esta em branco");
				}

			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSalvar.setBounds(315, 68, 89, 23);
		contentPane.add(btnSalvar);
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Pessoa pessoa = new Pessoa();

				int conf;
				conf = JOptionPane.showConfirmDialog(null, "tem certeza?", "deletar", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				if (conf == JOptionPane.YES_OPTION) {
					pessoa = lista.get(posicaoAtual);
					lista.remove(posicaoAtual);
					agd.remove(pessoa);

					if (posicaoAtual == lista.size()) {
						pessoa = lista.get(posicaoAtual -= 1);
						txtId.setText(String.valueOf(pessoa.getId()));
						txtNome.setText(pessoa.getNome());
						txtTelefone.setText(pessoa.getTelefone());
					}
				}
				pessoa = lista.get(posicaoAtual);
				txtId.setText(String.valueOf(pessoa.getId()));
				txtNome.setText(pessoa.getNome());
				txtTelefone.setText(pessoa.getTelefone());

			}
		});
		btnApagar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnApagar.setBounds(315, 171, 89, 23);
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
		btnAlterar.setBounds(315, 137, 89, 23);
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

				Pessoa pess = lista.get(posicaoAtual);
				txtId.setText(String.valueOf(pess.getId()));
				txtNome.setText(pess.getNome());
				txtTelefone.setText(pess.getTelefone());

			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancelar.setBounds(315, 102, 89, 23);

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
