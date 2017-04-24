package pcrn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.primefaces.event.data.FilterEvent;
import org.primefaces.model.StreamedContent;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import pcrn.interfaces.ICor;
import pcrn.interfaces.IFabricante;
import pcrn.interfaces.ILocal;
import pcrn.interfaces.IModelo;
import pcrn.interfaces.IPericia;
import pcrn.interfaces.ISeguro;
import pcrn.interfaces.ISituacao;
import pcrn.model.Cor;
import pcrn.model.Fabricante;
import pcrn.model.Local;
import pcrn.model.Modelo;
import pcrn.model.Pericia;
import pcrn.model.Pessoa;
import pcrn.model.Seguro;
import pcrn.model.Situacao;
import pcrn.model.Veiculo;
import pcrn.repository.Cores;
import pcrn.repository.Fabricantes;
import pcrn.repository.Locais;
import pcrn.repository.Modelos;
import pcrn.repository.Pericias;
import pcrn.repository.Seguros;
import pcrn.repository.Situacoes;
import pcrn.security.UsuarioSistema;
import pcrn.services.ModeloService;
import pcrn.services.VeiculoService;
import pcrn.util.FacesUtil;
import pcrn.util.report.ExecutorRelatorio;

@Named
@ViewScoped
public class CadastroVeiculoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private VeiculoService veiculoService;

	@Inject
	private ModeloService modeloService;
	
	@Inject
	private HttpServletResponse response;
	
	@Inject
	private EntityManager manager;
	
	@Inject
	private FacesContext facesContext;
	
	private Veiculo veiculo = new Veiculo();
	private Fabricante fabricante;
	
	private List<Veiculo> listaVeiculos;
	private List<Veiculo> listaVeiculosFiltrados;
	private Map<String, Object> mapaFiltros;
	private StreamedContent file;
	private String relatorio;
	private Map<String, Object> mapaParametro;
	private List<Modelo> listaModelos;
	
	
	public void lerFabricante(ValueChangeEvent evento){
		Fabricante fabricante = (Fabricante) evento.getNewValue();
		this.veiculo.setFabricante(fabricante);
		//return codigoSistemaOperacional;
	}
	
	public void cadastrar(){		
		String placas = "";
		Pessoa pessoa = new Pessoa();
		
		pessoa = getUsuarioLogado().getPessoa();
		
		//System.out.println("Chave: " + veiculo.getChave());
		//System.out.println("Lista de veiculos: " + veiculoService.chaveExistenteEditar(veiculo).size());
		
		if (veiculo.getChave().toUpperCase().equals("NAO") || 
				veiculo.getChave().toUpperCase().equals("NÃO") ||  
				veiculo.getChave().toUpperCase().equals("")) {
			
			veiculo.setPessoa(pessoa);
			
			if (veiculoService.placaxistenteCadastrar(veiculo).size() > 0) {
				FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_WARN, "","Esta placa j� foi cadastrada em outro carro"));
			}			
			veiculoService.salvar(veiculo);
			
			FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "","Veiculo Cadastrado"));
			
			
		}else{
			
			if (veiculoService.chaveExistenteCadastrar(veiculo).size() > 0) {
				List<Veiculo> listaVeiculo = veiculoService.chaveExistenteEditar(veiculo);	
				for (int i = 0; i < listaVeiculo.size(); i++) {
					placas = placas + " " + listaVeiculo.get(i).getPlaca();
				}
				FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Local da Chave ja ocupado por veiculos de placas "+ placas));
			}else{
				veiculo.setPessoa(pessoa);
				
				if (veiculoService.placaxistenteCadastrar(veiculo).size() > 0) {
					FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_WARN, "","Esta placa ja foi cadastrada em outro carro"));
				}
				
				veiculoService.salvar(veiculo);
				FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "","Veiculo Cadastrado"));
				FacesContext.getCurrentInstance()
			      .getExternalContext()
			      .getFlash().setKeepMessages(true);
				
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("Veiculo2.xhtml");
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void editar(){
		
		String placas = "";
		Pessoa pessoa = new Pessoa();
		pessoa = getUsuarioLogado().getPessoa();		
		
		
		//System.out.println("Chave: " + veiculo.getChave());
		//System.out.println("Lista de veiculos: " + veiculoService.chaveExistenteEditar(veiculo).size());
		
		if (veiculo.getChave().toUpperCase().equals("NAO") || 
				veiculo.getChave().toUpperCase().equals("NÃO") || 
				veiculo.getChave().toUpperCase().equals("")) {
			
			veiculo.setPessoa(pessoa);
			veiculoService.editar(veiculo);
			
			FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "","Veiculo Editado"));
			
		}else{
			if (veiculoService.chaveExistenteEditar(veiculo).size() > 0) {
				List<Veiculo> listaVeiculo = veiculoService.chaveExistenteEditar(veiculo);	
				for (int i = 0; i < listaVeiculo.size(); i++) {
					placas = placas + " " + listaVeiculo.get(i).getPlaca();
				}
				FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Local da Chave ja ocupado por veiculos de placas "+ placas));
			}else{
				veiculo.setPessoa(pessoa);				
				veiculoService.editar(veiculo);
				FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(FacesMessage.SEVERITY_INFO, "","Veiculo Editado"));
				
			}
		}
	}
	
	private UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) 
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		
		return usuario;
	}
	
	public String ocorrencia(ActionEvent event){
		Veiculo veiculo = (Veiculo) event.getComponent().getAttributes().get("codigo");
		String codigo = veiculo.getCodigo().toString();
		return "Ocorrencia?codigo="+codigo+"faces-redirect=true";
	}

	public void excluir(Veiculo veiculo){
		veiculoService.remover(veiculo);
	}
	
	 public void listenFilter(FilterEvent event) {
	        // update datasource
	        Map<String, Object> tempString = event.getFilters();
	        ArrayList<String> listaObjetos = new ArrayList<String>();
	        ArrayList<String> listaValores = new ArrayList<String>();
	        String relatorio = "";
	        Map<String, Object> mapa = null;
	        
	        
	        System.out.println("size filter: "+ tempString.size());
	        FacesUtil util = new FacesUtil();
	        
	        for (String key : tempString.keySet()) {
	            System.out.println("key: " + key + " \t values: "
	                    + tempString.get(key).toString().toUpperCase());
	            
	        
	        listaObjetos.add(key);
	        listaValores.add(tempString.get(key).toString().toUpperCase());
	        	}
	        relatorio = util.escolherRelatorio(listaObjetos);
	        System.out.println("Relatorio: " + relatorio);
	        mapa = this.retornarParametros(listaObjetos, listaValores);
	        
	        
	            /*for (String key : mapaRelatorioParametro.keySet()) {
	            	System.out.println("Nome do codigo: " + key + " \t Relatorio: "
		                    + mapaRelatorioParametro.get(key).toString());
	            	parametro = key;
	            	relatorio = mapaRelatorioParametro.get(key).toString();
	            }*/
	        
	        System.out.println("Relatorio: " + relatorio);
	        this.setMapaParametro(mapa);
	        this.setRelatorio(relatorio);
	    }
	
	 
	 public void gerarRelatorio() {
			Map<String, Object> parametros = new HashMap<>();
			parametros = this.getMapaParametro();
			String caminhoRelatorio = relatorio;
			
			System.out.println("Caminho relatorio: " + caminhoRelatorio);
			
			ExecutorRelatorio executor = new ExecutorRelatorio(caminhoRelatorio,
					this.response, parametros, "Relatorio.pdf");
			
			Session session = manager.unwrap(Session.class);
			session.doWork(executor);
			
			if (executor.isRelatorioGerado()) {
				facesContext.responseComplete();
			} else {
				FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
			}
		}
	 
	 
	 public void gerarRelatorioVeiculo(String codigoVeiculo) {
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("codigo_veiculo", codigoVeiculo);
			System.out.println("Codigo veiculo: " + codigoVeiculo);
			ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/RelatorioVeiculo.jasper",
					this.response, parametros, "Relatorio.pdf");
			
			Session session = manager.unwrap(Session.class);
			session.doWork(executor);
			
			if (executor.isRelatorioGerado()) {
				facesContext.responseComplete();
			} else {
				FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
			}
		}
	 
	 
	 public List<Veiculo> listarVeiculos(){
		 
		 listaVeiculos = veiculoService.listar();
		 
		 return listaVeiculos;
	 }
	 
	 
	 
	 public String redirecionaParaFoto(Veiculo veiculo){
			
		 return "/site/Foto/Edicao/Foto.xhtml?codigoVeiculo="+veiculo.getCodigo()+"faces-redirect=true";
		 
	}
	 
	 public String redirecionaParaOcorrencia(Veiculo veiculo){
			
		 return "/site/Ocorrencia/Consulta/Ocorrencia.xhtml?codigoVeiculo="+veiculo.getCodigo()+"faces-redirect=true";
	} 
	 
	
	 public String novo(){
			
			String pagina = "/site/Veiculo/Novo/Veiculo.xhtml?faces-redirect=true";
			
			return pagina;
		} 
	 
	 
	 public void carregaModelos(){
		 System.out.println("Entrou no metodo");
		 listaModelos = modeloService.buscarModelos(veiculo.getFabricante());
		}
	 
	 
	 private Map<String, Object> retornarParametros(ArrayList<String> listaObjetos, ArrayList<String> listavalores){
			String parametro = "";
			Map<String, Object> mapaParametro = new HashMap<String, Object>();
			int valor = 0;
			
			//for (int i = 0; i < listaObjetos.size(); i++) {
				//System.out.println("Lista: "+ listaObjetos.get(i));
			
				if (listaObjetos.size() == 1) {
					//System.out.println("Parametro 1");
		            if (listaObjetos.get(0).equals("fabricante.fabricante")) {
		            	Fabricante fabricante = new Fabricante();
		        		IFabricante fabricantes = new Fabricantes();
		        		
		        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
		            	
		        		valor = fabricante.getCodigo();
		            	parametro = "codigo_fabricante";
		            	
		            	mapaParametro.put(parametro, valor);
					}else if (listaObjetos.get(0).equals("modelo.modelo")) {
						
						Modelo modelo = new Modelo();
						IModelo modelos = new Modelos();
						
						modelo = modelos.pegaCodigo(listavalores.get(0));
						
						valor = modelo.getCodigo();
		            	parametro = "codigo_modelo";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("cor.cor")) {
						Cor cor = new Cor();
						ICor cores = new Cores();
						System.out.println("Codigo Cor: " + listavalores.get(0));
						
						cor = cores.pegaCodigo(listavalores.get(0));
						
						valor = cor.getCodigo();
		            	parametro = "codigo_cor";
		            	
		            	mapaParametro.put(parametro, valor);		
						
					}else if (listaObjetos.get(0).equals("seguro.seguro")) {
						Seguro seguro = new Seguro();
						ISeguro seguros = new Seguros();
						seguro = seguros.pegaCodigo(listavalores.get(0));
						
						valor = seguro.getCodigo();
		            	parametro = "codigo_seguro";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
					}else if (listaObjetos.get(0).equals("pericia.pericia")) {
						Pericia pericia = new Pericia();
						IPericia pericias = new Pericias();
						pericia = pericias.pegaCodigo(listavalores.get(0));
						
						valor = pericia.getCodigo();
		            	parametro = "codigo_pericia";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("situacao.situacao")) {
						Situacao situacao = new Situacao();
						ISituacao ISituacao = new Situacoes();
						situacao = ISituacao.pegaCodigo(listavalores.get(0));
						
						valor = situacao.getCodigo();
		            	parametro = "codigo_situacao";
		            	
		            	mapaParametro.put(parametro, valor);	            	
					
					}else if (listaObjetos.get(0).equals("local.local")) {
						Local local = new Local();
						ILocal Ilocal = new Locais();
						
						
						local = Ilocal.pegaCodigo(listavalores.get(0));
						
						valor = local.getCodigo();
		            	parametro = "codigo_local";
		            	
		            	mapaParametro.put(parametro, valor);	            	
					}
		            
				}else if (listaObjetos.size() == 2) {
					//System.out.println("Parametro 2");
					if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("cor.cor")) {
						Fabricante fabricante = new Fabricante();
		        		IFabricante fabricantes = new Fabricantes();
		        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
		            	
		        		valor = fabricante.getCodigo();
		            	parametro = "codigo_fabricante";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
		            	Cor cor = new Cor();
						ICor cores = new Cores();
						cor = cores.pegaCodigo(listavalores.get(1));
						
						valor = cor.getCodigo();
		            	parametro = "codigo_cor";
		            	
		            	mapaParametro.put(parametro, valor);
						
						
					}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("fabricante.fabricante")) {
						Cor cor = new Cor();
						ICor cores = new Cores();
						cor = cores.pegaCodigo(listavalores.get(0));
						
						valor = cor.getCodigo();
		            	parametro = "codigo_cor";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
		            	Fabricante fabricante = new Fabricante();
		        		IFabricante fabricantes = new Fabricantes();
		        		fabricante = fabricantes.pegaCodigo(listavalores.get(1));
		            	
		        		valor = fabricante.getCodigo();
		            	parametro = "codigo_fabricante";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("cor.cor")) {
						Modelo modelo = new Modelo();
						IModelo modelos = new Modelos();
						modelo = modelos.pegaCodigo(listavalores.get(0));
						
						valor = modelo.getCodigo();
		            	parametro = "codigo_modelo";
		            	
		            	mapaParametro.put(parametro, valor);
			            		        	           	
			            Cor cor = new Cor();
						ICor cores = new Cores();
						cor = cores.pegaCodigo(listavalores.get(1));
							
						valor = cor.getCodigo();
			            parametro = "codigo_cor";
			            	
			            mapaParametro.put(parametro, valor);
							
					}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("modelo.modelo")) {
						Cor cor = new Cor();
						ICor cores = new Cores();
						cor = cores.pegaCodigo(listavalores.get(0));
							
						valor = cor.getCodigo();
			            parametro = "codigo_cor";
			            	
			            mapaParametro.put(parametro, valor);
						
						Modelo modelo = new Modelo();
						IModelo modelos = new Modelos();
						modelo = modelos.pegaCodigo(listavalores.get(1));
						
						valor = modelo.getCodigo();
		            	parametro = "codigo_modelo";
		            	
		            	mapaParametro.put(parametro, valor);
			            
					}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("cor.cor"))  {
						Seguro seguro = new Seguro();
						ISeguro seguros = new Seguros();
						seguro = seguros.pegaCodigo(listavalores.get(0));
						
						valor = seguro.getCodigo();
		            	parametro = "codigo_seguro";
		            	
		            	mapaParametro.put(parametro, valor);
						
		            	Cor cor = new Cor();
						ICor cores = new Cores();
						cor = cores.pegaCodigo(listavalores.get(1));
							
						valor = cor.getCodigo();
			            parametro = "codigo_cor";
			            	
			            mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("seguro.seguro")) {
						Cor cor = new Cor();
						ICor cores = new Cores();
						cor = cores.pegaCodigo(listavalores.get(0));
							
						valor = cor.getCodigo();
			            parametro = "codigo_cor";
			            	
			            mapaParametro.put(parametro, valor);
						
			            Seguro seguro = new Seguro();
						ISeguro seguros = new Seguros();
						seguro = seguros.pegaCodigo(listavalores.get(1));
						
						valor = seguro.getCodigo();
		            	parametro = "codigo_seguro";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("pericia.pericia")) {
						Cor cor = new Cor();
						ICor cores = new Cores();
						cor = cores.pegaCodigo(listavalores.get(0));
							
						valor = cor.getCodigo();
			            parametro = "codigo_cor";
			            	
			            mapaParametro.put(parametro, valor);
						
			            Pericia pericia = new Pericia();
						IPericia pericias = new Pericias();
						pericia = pericias.pegaCodigo(listavalores.get(1));
						
						valor = pericia.getCodigo();
		            	parametro = "codigo_pericia";
		            	
		            	mapaParametro.put(parametro, valor);
			            
					}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("cor.cor")) {	
						Pericia pericia = new Pericia();
						IPericia pericias = new Pericias();
						pericia = pericias.pegaCodigo(listavalores.get(0));
						
						valor = pericia.getCodigo();
		            	parametro = "codigo_pericia";
		            	
		            	mapaParametro.put(parametro, valor);
						
		            	Cor cor = new Cor();
						ICor cores = new Cores();
						cor = cores.pegaCodigo(listavalores.get(1));
							
						valor = cor.getCodigo();
			            parametro = "codigo_cor";
			            	
			            mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("situacao.situacao")) {
						Cor cor = new Cor();
						ICor cores = new Cores();
						cor = cores.pegaCodigo(listavalores.get(0));
							
						valor = cor.getCodigo();
			            parametro = "codigo_cor";
			            	
			            mapaParametro.put(parametro, valor);
			            
			            Situacao situacao = new Situacao();
						ISituacao ISituacao = new Situacoes();
						situacao = ISituacao.pegaCodigo(listavalores.get(1));
						
						valor = situacao.getCodigo();
		            	parametro = "codigo_situacao";
		            	
		            	mapaParametro.put(parametro, valor);		            
			            
					}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("cor.cor")) {
						Situacao situacao = new Situacao();
						ISituacao ISituacao = new Situacoes();
						situacao = ISituacao.pegaCodigo(listavalores.get(0));
						
						valor = situacao.getCodigo();
		            	parametro = "codigo_situacao";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
		            	Cor cor = new Cor();
						ICor cores = new Cores();
						cor = cores.pegaCodigo(listavalores.get(1));
							
						valor = cor.getCodigo();
			            parametro = "codigo_cor";
			            	
			            mapaParametro.put(parametro, valor);
		            	
					}else if (listaObjetos.get(0).equals("local.local")  && listaObjetos.get(1).equals("cor.cor")) {
						Local local = new Local();
						ILocal Ilocal = new Locais();
						
						
						local = Ilocal.pegaCodigo(listavalores.get(0));
						
						valor = local.getCodigo();
		            	parametro = "codigo_local";
		            	
		            	mapaParametro.put(parametro, valor);
			            		        	           	
			            Cor cor = new Cor();
						ICor cores = new Cores();
						cor = cores.pegaCodigo(listavalores.get(1));
							
						valor = cor.getCodigo();
			            parametro = "codigo_cor";
			            	
			            mapaParametro.put(parametro, valor);
							
					}else if (listaObjetos.get(0).equals("cor.cor") && listaObjetos.get(1).equals("local.local")) {
						Cor cor = new Cor();
						ICor cores = new Cores();
						cor = cores.pegaCodigo(listavalores.get(0));
							
						valor = cor.getCodigo();
			            parametro = "codigo_cor";
			            	
			            mapaParametro.put(parametro, valor);
						
			            Local local = new Local();
						ILocal Ilocal = new Locais();
						
						
						local = Ilocal.pegaCodigo(listavalores.get(1));
						
						valor = local.getCodigo();
		            	parametro = "codigo_local";
		            	
		            	mapaParametro.put(parametro, valor);
			            
					}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("seguro.seguro")) {
						Fabricante fabricante = new Fabricante();
		        		IFabricante fabricantes = new Fabricantes();
		        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
		            	
		        		valor = fabricante.getCodigo();
		            	parametro = "codigo_fabricante";
		            	
		            	mapaParametro.put(parametro, valor); 
						
						Seguro seguro = new Seguro();
						ISeguro seguros = new Seguros();
						seguro = seguros.pegaCodigo(listavalores.get(1));
						
						valor = seguro.getCodigo();
		            	parametro = "codigo_seguro";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("seguro.seguro") && listaObjetos.get(1).equals("fabricante.fabricante")) {
						Seguro seguro = new Seguro();
						ISeguro seguros = new Seguros();
						seguro = seguros.pegaCodigo(listavalores.get(0));
						
						valor = seguro.getCodigo();
		            	parametro = "codigo_seguro";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
		            	Fabricante fabricante = new Fabricante();
		        		IFabricante fabricantes = new Fabricantes();
		        		fabricante = fabricantes.pegaCodigo(listavalores.get(1));
		            	
		        		valor = fabricante.getCodigo();
		            	parametro = "codigo_fabricante";
		            	
		            	mapaParametro.put(parametro, valor);
					}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("pericia.pericia")) {
						Fabricante fabricante = new Fabricante();
		        		IFabricante fabricantes = new Fabricantes();
		        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
		            	
		        		valor = fabricante.getCodigo();
		            	parametro = "codigo_fabricante";
		            	
		            	mapaParametro.put(parametro, valor); 
						
		            	Pericia pericia = new Pericia();
						IPericia pericias = new Pericias();
						pericia = pericias.pegaCodigo(listavalores.get(1));
						
						valor = pericia.getCodigo();
		            	parametro = "codigo_pericia";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("fabricante.fabricante")) {
						Pericia pericia = new Pericia();
						IPericia pericias = new Pericias();
						pericia = pericias.pegaCodigo(listavalores.get(0));
						
						valor = pericia.getCodigo();
		            	parametro = "codigo_pericia";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
		            	Fabricante fabricante = new Fabricante();
		        		IFabricante fabricantes = new Fabricantes();
		        		fabricante = fabricantes.pegaCodigo(listavalores.get(1));
		            	
		        		valor = fabricante.getCodigo();
		            	parametro = "codigo_fabricante";
		            	
		            	mapaParametro.put(parametro, valor);
					}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("situacao.situacao")) {
						Fabricante fabricante = new Fabricante();
		        		IFabricante fabricantes = new Fabricantes();
		        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
		            	
		        		valor = fabricante.getCodigo();
		            	parametro = "codigo_fabricante";
		            	
		            	mapaParametro.put(parametro, valor); 
						
		            	Situacao situacao = new Situacao();
						ISituacao ISituacao = new Situacoes();
						situacao = ISituacao.pegaCodigo(listavalores.get(1));
						
						valor = situacao.getCodigo();
		            	parametro = "codigo_situacao";
		            	
		            	mapaParametro.put(parametro, valor);					
						
					}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("fabricante.fabricante")) {
						Situacao situacao = new Situacao();
						ISituacao ISituacao = new Situacoes();
						situacao = ISituacao.pegaCodigo(listavalores.get(0));
						
						valor = situacao.getCodigo();
		            	parametro = "codigo_situacao";
		            	
		            	mapaParametro.put(parametro, valor);					
						
						Fabricante fabricante = new Fabricante();
		        		IFabricante fabricantes = new Fabricantes();
		        		fabricante = fabricantes.pegaCodigo(listavalores.get(1));
		            	
		        		valor = fabricante.getCodigo();
		            	parametro = "codigo_fabricante";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("fabricante.fabricante")  && listaObjetos.get(1).equals("local.local")) {
						Fabricante fabricante = new Fabricante();
		        		IFabricante fabricantes = new Fabricantes();
		        		fabricante = fabricantes.pegaCodigo(listavalores.get(0));
		            	
		        		valor = fabricante.getCodigo();
		            	parametro = "codigo_fabricante";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
		            	Local local = new Local();
						ILocal Ilocal = new Locais();
						
						
						local = Ilocal.pegaCodigo(listavalores.get(1));
						
						valor = local.getCodigo();
		            	parametro = "codigo_local";
		            	
		            	mapaParametro.put(parametro, valor);	
						
					}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("fabricante.fabricante")) {
						Local local = new Local();
						ILocal Ilocal = new Locais();
						
						local = Ilocal.pegaCodigo(listavalores.get(0));
						
						valor = local.getCodigo();
		            	parametro = "codigo_local";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
		            	Fabricante fabricante = new Fabricante();
		        		IFabricante fabricantes = new Fabricantes();
		        		fabricante = fabricantes.pegaCodigo(listavalores.get(1));
		            	
		        		valor = fabricante.getCodigo();
		            	parametro = "codigo_fabricante";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("seguro.seguro")) {
						Modelo modelo = new Modelo();
						IModelo modelos = new Modelos();
						modelo = modelos.pegaCodigo(listavalores.get(0));
						
						valor = modelo.getCodigo();
		            	parametro = "codigo_modelo";
		            	
		            	mapaParametro.put(parametro, valor); 
						
						Seguro seguro = new Seguro();
						ISeguro seguros = new Seguros();
						seguro = seguros.pegaCodigo(listavalores.get(1));
						
						valor = seguro.getCodigo();
		            	parametro = "codigo_seguro";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("seguro.seguro") && listaObjetos.get(1).equals("modelo.modelo")) {
						Seguro seguro = new Seguro();
						ISeguro seguros = new Seguros();
						seguro = seguros.pegaCodigo(listavalores.get(0));
						
						valor = seguro.getCodigo();
		            	parametro = "codigo_seguro";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
		            	Modelo modelo = new Modelo();
						IModelo modelos = new Modelos();
						modelo = modelos.pegaCodigo(listavalores.get(1));
						
						valor = modelo.getCodigo();
		            	parametro = "codigo_modelo";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
					}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("pericia.pericia")) {
						Modelo modelo = new Modelo();
						IModelo modelos = new Modelos();
						modelo = modelos.pegaCodigo(listavalores.get(0));
						
						valor = modelo.getCodigo();
		            	parametro = "codigo_modelo";
		            	
		            	mapaParametro.put(parametro, valor); 
						
		            	Pericia pericia = new Pericia();
						IPericia pericias = new Pericias();
						pericia = pericias.pegaCodigo(listavalores.get(1));
						
						valor = pericia.getCodigo();
		            	parametro = "codigo_pericia";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("modelo.modelo")) {
						Pericia pericia = new Pericia();
						IPericia pericias = new Pericias();
						pericia = pericias.pegaCodigo(listavalores.get(0));
						
						valor = pericia.getCodigo();
		            	parametro = "codigo_pericia";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
		            	Modelo modelo = new Modelo();
						IModelo modelos = new Modelos();
						modelo = modelos.pegaCodigo(listavalores.get(1));
						
						valor = modelo.getCodigo();
		            	parametro = "codigo_modelo";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
					}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("situacao.situacao")) {
						Modelo modelo = new Modelo();
						IModelo modelos = new Modelos();
						modelo = modelos.pegaCodigo(listavalores.get(0));
						
						valor = modelo.getCodigo();
		            	parametro = "codigo_modelo";
		            	
		            	mapaParametro.put(parametro, valor); 
						
		            	Situacao situacao = new Situacao();
						ISituacao ISituacao = new Situacoes();
						situacao = ISituacao.pegaCodigo(listavalores.get(1));
						
						valor = situacao.getCodigo();
		            	parametro = "codigo_situacao";
		            	
		            	mapaParametro.put(parametro, valor);					
						
					}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("modelo.modelo")) {
						Situacao situacao = new Situacao();
						ISituacao ISituacao = new Situacoes();
						situacao = ISituacao.pegaCodigo(listavalores.get(0));
						
						valor = situacao.getCodigo();
		            	parametro = "codigo_situacao";
		            	
		            	mapaParametro.put(parametro, valor);					
						
		            	Modelo modelo = new Modelo();
						IModelo modelos = new Modelos();
						modelo = modelos.pegaCodigo(listavalores.get(1));
						
						valor = modelo.getCodigo();
		            	parametro = "codigo_modelo";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("modelo.modelo")  && listaObjetos.get(1).equals("local.local")) {
						Modelo modelo = new Modelo();
						IModelo modelos = new Modelos();
						modelo = modelos.pegaCodigo(listavalores.get(0));
						
						valor = modelo.getCodigo();
		            	parametro = "codigo_modelo";
		            	
		            	mapaParametro.put(parametro, valor); 
						
		            	Local local = new Local();
						ILocal Ilocal = new Locais();
						
						local = Ilocal.pegaCodigo(listavalores.get(1));
						
						valor = local.getCodigo();
		            	parametro = "codigo_local";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("modelo.modelo")) {
						Local local = new Local();
						ILocal Ilocal = new Locais();
						
						local = Ilocal.pegaCodigo(listavalores.get(0));
						
						valor = local.getCodigo();
		            	parametro = "codigo_local";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
		            	Modelo modelo = new Modelo();
						IModelo modelos = new Modelos();
						modelo = modelos.pegaCodigo(listavalores.get(1));
						
						valor = modelo.getCodigo();
		            	parametro = "codigo_modelo";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
					}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("pericia.pericia")) {
						Seguro seguro = new Seguro();
						ISeguro seguros = new Seguros();
						seguro = seguros.pegaCodigo(listavalores.get(0));
						
						valor = seguro.getCodigo();
		            	parametro = "codigo_seguro";
		            	
		            	mapaParametro.put(parametro, valor);
						
						Pericia pericia = new Pericia();
						IPericia pericias = new Pericias();
						pericia = pericias.pegaCodigo(listavalores.get(1));
						
						valor = pericia.getCodigo();
		            	parametro = "codigo_pericia";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("pericia.pericia") && listaObjetos.get(1).equals("seguro.seguro")) {
						Pericia pericia = new Pericia();
						IPericia pericias = new Pericias();
						pericia = pericias.pegaCodigo(listavalores.get(0));
						
						valor = pericia.getCodigo();
		            	parametro = "codigo_pericia";
		            	
		            	mapaParametro.put(parametro, valor);
						
		            	Seguro seguro = new Seguro();
						ISeguro seguros = new Seguros();
						seguro = seguros.pegaCodigo(listavalores.get(1));
						
						valor = seguro.getCodigo();
		            	parametro = "codigo_seguro";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
					}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("situacao.situacao")) {
						Seguro seguro = new Seguro();
						ISeguro seguros = new Seguros();
						seguro = seguros.pegaCodigo(listavalores.get(0));
						
						valor = seguro.getCodigo();
		            	parametro = "codigo_seguro";
		            	
		            	mapaParametro.put(parametro, valor);
						
						Situacao situacao = new Situacao();
						ISituacao ISituacao = new Situacoes();
						situacao = ISituacao.pegaCodigo(listavalores.get(1));
						
						valor = situacao.getCodigo();
		            	parametro = "codigo_situacao";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("seguro.seguro")) {
						Situacao situacao = new Situacao();
						ISituacao ISituacao = new Situacoes();
						situacao = ISituacao.pegaCodigo(listavalores.get(0));
						
						valor = situacao.getCodigo();
		            	parametro = "codigo_situacao";
		            	
		            	mapaParametro.put(parametro, valor);
						
		            	Seguro seguro = new Seguro();
						ISeguro seguros = new Seguros();
						seguro = seguros.pegaCodigo(listavalores.get(1));
						
						valor = seguro.getCodigo();
		            	parametro = "codigo_seguro";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
					}else if (listaObjetos.get(0).equals("seguro.seguro")  && listaObjetos.get(1).equals("local.local")) {
						Seguro seguro = new Seguro();
						ISeguro seguros = new Seguros();
						seguro = seguros.pegaCodigo(listavalores.get(0));
						
						valor = seguro.getCodigo();
		            	parametro = "codigo_seguro";
		            	
		            	mapaParametro.put(parametro, valor);
						
		            	Local local = new Local();
						ILocal Ilocal = new Locais();
						
						local = Ilocal.pegaCodigo(listavalores.get(1));
						
						valor = local.getCodigo();
		            	parametro = "codigo_local";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("seguro.seguro")) {
						Local local = new Local();
						ILocal Ilocal = new Locais();
						
						local = Ilocal.pegaCodigo(listavalores.get(0));
						
						valor = local.getCodigo();
		            	parametro = "codigo_local";
		            	
		            	mapaParametro.put(parametro, valor);
						
		            	Seguro seguro = new Seguro();
						ISeguro seguros = new Seguros();
						seguro = seguros.pegaCodigo(listavalores.get(1));
						
						valor = seguro.getCodigo();
		            	parametro = "codigo_seguro";
		            	
		            	mapaParametro.put(parametro, valor);
					}else if (listaObjetos.get(0).equals("pericia.pericia")  && listaObjetos.get(1).equals("situacao.situacao")) {
						Pericia pericia = new Pericia();
						IPericia pericias = new Pericias();
						pericia = pericias.pegaCodigo(listavalores.get(0));
						
						valor = pericia.getCodigo();
		            	parametro = "codigo_pericia";
		            	
		            	mapaParametro.put(parametro, valor);
						
						Situacao situacao = new Situacao();
						ISituacao ISituacao = new Situacoes();
						situacao = ISituacao.pegaCodigo(listavalores.get(1));
						
						valor = situacao.getCodigo();
		            	parametro = "codigo_situacao";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("situacao.situacao") && listaObjetos.get(1).equals("pericia.pericia")) {
						Situacao situacao = new Situacao();
						ISituacao ISituacao = new Situacoes();
						situacao = ISituacao.pegaCodigo(listavalores.get(0));
						
						valor = situacao.getCodigo();
		            	parametro = "codigo_situacao";
		            	
		            	mapaParametro.put(parametro, valor);
						
		            	Pericia pericia = new Pericia();
						IPericia pericias = new Pericias();
						pericia = pericias.pegaCodigo(listavalores.get(1));
						
						valor = pericia.getCodigo();
		            	parametro = "codigo_pericia";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
					}else if (listaObjetos.get(0).equals("pericia.pericia")  && listaObjetos.get(1).equals("local.local")) {
						Pericia pericia = new Pericia();
						IPericia pericias = new Pericias();
						pericia = pericias.pegaCodigo(listavalores.get(0));
						
						valor = pericia.getCodigo();
		            	parametro = "codigo_pericia";
		            	
		            	mapaParametro.put(parametro, valor);
						
						
		            	Local local = new Local();
						ILocal Ilocal = new Locais();
						
						local = Ilocal.pegaCodigo(listavalores.get(1));
						
						valor = local.getCodigo();
		            	parametro = "codigo_local";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("pericia.pericia")) {
						Local local = new Local();
						ILocal Ilocal = new Locais();
						
						local = Ilocal.pegaCodigo(listavalores.get(0));
						
						valor = local.getCodigo();
		            	parametro = "codigo_local";
		            	
		            	mapaParametro.put(parametro, valor);
						
		            	Pericia pericia = new Pericia();
						IPericia pericias = new Pericias();
						pericia = pericias.pegaCodigo(listavalores.get(1));
						
						valor = pericia.getCodigo();
		            	parametro = "codigo_pericia";
		            	
		            	mapaParametro.put(parametro, valor);
		            	
					}else if (listaObjetos.get(0).equals("situacao.situacao")  && listaObjetos.get(1).equals("local.local")) {
						Situacao situacao = new Situacao();
						ISituacao ISituacao = new Situacoes();
						situacao = ISituacao.pegaCodigo(listavalores.get(0));
						
						valor = situacao.getCodigo();
		            	parametro = "codigo_situacao";
		            	
		            	mapaParametro.put(parametro, valor);
						
						
		            	Local local = new Local();
						ILocal Ilocal = new Locais();
						
						local = Ilocal.pegaCodigo(listavalores.get(1));
						
						valor = local.getCodigo();
		            	parametro = "codigo_local";
		            	
		            	mapaParametro.put(parametro, valor);
						
					}else if (listaObjetos.get(0).equals("local.local") && listaObjetos.get(1).equals("situacao.situacao")) {
						Local local = new Local();
						ILocal Ilocal = new Locais();
						
						local = Ilocal.pegaCodigo(listavalores.get(0));
						
						valor = local.getCodigo();
		            	parametro = "codigo_local";
		            	
		            	mapaParametro.put(parametro, valor);
						
		            	Situacao situacao = new Situacao();
						ISituacao ISituacao = new Situacoes();
						situacao = ISituacao.pegaCodigo(listavalores.get(1));
						
						valor = situacao.getCodigo();
		            	parametro = "codigo_situacao";
		            	
		            	mapaParametro.put(parametro, valor);
						}
					}
			//}
			return mapaParametro;
		}	
	 
	 
	 
	 
	 
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public Veiculo getVeiculo() {
			return veiculo;
		}


	public void setVeiculo(Veiculo veiculo) {
			this.veiculo = veiculo;
		}


	public List<Veiculo> getListaVeiculos() {
			return listaVeiculos;
		}

	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
			this.listaVeiculos = listaVeiculos;
		}

	public List<Veiculo> getListaVeiculosFiltrados() {
			return listaVeiculosFiltrados;
		}

	public void setListaVeiculosFiltrados(List<Veiculo> listaVeiculosFiltrados) {
			this.listaVeiculosFiltrados = listaVeiculosFiltrados;
		}

	public Map<String, Object> getMapaFiltros() {
			return mapaFiltros;
		}

	public void setMapaFiltros(Map<String, Object> mapaFiltros) {
			this.mapaFiltros = mapaFiltros;
		}

	public StreamedContent getFile() {
			return file;
		}

	public void setFile(StreamedContent file) {
			this.file = file;
		}

	public String getRelatorio() {
			return relatorio;
		}

	public void setRelatorio(String relatorio) {
			this.relatorio = relatorio;
		}



	public Map<String, Object> getMapaParametro() {
			return mapaParametro;
		}


	public void setMapaParametro(Map<String, Object> mapaParametro) {
			this.mapaParametro = mapaParametro;
		}

	public List<Modelo> getListaModelos() {
		return listaModelos;
		}

	public void setListaModelos(List<Modelo> listaModelos) {
			this.listaModelos = listaModelos;
		}	
	
}
