package br.com.leslivros.web.command.imp;

import br.com.leslivros.controle.Fachada;
import br.com.leslivros.inter.IFachada;
import br.com.leslivros.web.command.ICommand;

public abstract class AbstractCommand implements ICommand {

	protected IFachada fachada = new Fachada();
}