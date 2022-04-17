package br.edu.ufersa.LeMenu.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.LeMenu.Dto.ClientRegisterDto;
import br.edu.ufersa.LeMenu.model.Client;
import br.edu.ufersa.LeMenu.model.OrderingTable;
import br.edu.ufersa.LeMenu.repository.ClientRepository;
import br.edu.ufersa.LeMenu.repository.OrderingTableRepository;
import br.edu.ufersa.LeMenu.service.ClientServices;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientServices service;
	@Autowired
	private ClientRepository clientRepos;
	@Autowired
	private OrderingTableRepository tbRepos;

	@GetMapping("/search/all")
	public List<Client> findAllActive() {
		return service.findAll();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		Client c = clientRepos.findById(id).get();
		OrderingTable tb = tbRepos.findById(c.getTable().getId()).get();

		tb.setClient(null);
		var tbTemp = tbRepos.save(tb);
		if (tbTemp == null) {
			return ResponseEntity.notFound().build();
		} else {
			c.setTable(null);
			var clientTemp = clientRepos.save(c);
			if (clientTemp == null) {
				return ResponseEntity.notFound().build();
			} else {
				clientRepos.delete(clientTemp);
				return ResponseEntity.ok("OK");
			}
		} /*
			 * return clientRepos.findById(id).map(table -> { clientRepos.delete(table);
			 * return ResponseEntity.ok("OK"); }).orElse(ResponseEntity.notFound().build());
			 */
	}

	@PostMapping("/new")
	public ResponseEntity<Long> save(@RequestBody ClientRegisterDto modelDto) {

		Client client = new Client();
		client.setName(modelDto.getName());

		OrderingTable tb = tbRepos.findById(modelDto.getTableId()).get();

		/*
		 * if(!tb.isOpen()) { return ResponseEntity.badRequest().build(); } else { }
		 */
		if (tb == null || !tb.getIsOpen()) {
			return ResponseEntity.badRequest().build();
		} else {

			//client.setTable(tb);
			tb.setClient(client);
			tb.setOpen(false);
			var clientTemp = clientRepos.save(client);
			var tbTemp = tbRepos.save(tb);
			if (clientTemp == null || tbTemp == null ) {
				return ResponseEntity.badRequest().build();
			} else {
				return ResponseEntity.status(HttpStatus.CREATED).body(clientTemp.getId());
			}
		}
	}
}
