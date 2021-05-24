package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.donus.challenge.api.account.management.BankAccountManagementApplication;
import com.donus.challenge.api.account.management.controller.ContaController;
import com.donus.challenge.api.account.management.model.dto.ContaDTO;
import com.donus.challenge.api.account.management.model.dto.TransacaoDTO;
import com.donus.challenge.api.account.management.model.request.ClienteRequest;
import com.donus.challenge.api.account.management.model.request.TransacaoRequest;

/**
 * @author andreia
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BankAccountManagementApplication.class)
public class ContaControllerTest {

	@Autowired
	private ContaController restController;

	ClienteRequest clienteRequest;

	TransacaoRequest transacaoRequestDeposit;

	TransacaoRequest transacaoRequestTransfer;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setCpf("12864644479");
		clienteRequest.setNomeCompleto("Andréia");
		this.clienteRequest = clienteRequest;

		TransacaoRequest transacaoRequestDeposit = new TransacaoRequest();
		transacaoRequestDeposit.setDescricao("Depositando");
		transacaoRequestDeposit.setValor(new BigDecimal(50));
		this.transacaoRequestDeposit = transacaoRequestDeposit;

		TransacaoRequest transacaoRequestTransfer = new TransacaoRequest();
		transacaoRequestTransfer.setDescricao("Transferindo");
		transacaoRequestTransfer.setValor(new BigDecimal(2));
		this.transacaoRequestTransfer = transacaoRequestTransfer;
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void openAccoutTeste() throws Exception {

		ContaDTO contaDTO = new ContaDTO();
		ResponseEntity<ContaDTO> contaResponse = this.restController.openAccountUser(clienteRequest, contaDTO);

		assertEquals("12864644479", contaResponse.getBody().getCliente().getCpf());

	}

	/**
	 * @throws Exception
	 */
	@Test
	public void depositTeste() throws Exception {

		ResponseEntity<?> TransacaoResponse = this.restController.deposit("1810570859", transacaoRequestDeposit);

		assertEquals("Deposito realizado com sucesso", TransacaoResponse.getBody());

	}

	/**
	 * @throws Exception
	 */
	@Test
	public void transferTeste() throws Exception {

		ResponseEntity<TransacaoDTO> TransacaoResponse = this.restController.transfer("1810570859", "2694063415",
				transacaoRequestTransfer);

		assertEquals("Transferindo", TransacaoResponse.getBody().getDescricao());

	}

}
