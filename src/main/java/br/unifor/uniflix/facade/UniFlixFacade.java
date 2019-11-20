package br.unifor.uniflix.facade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.unifor.uniflix.adapter.FilmeJSON;
import br.unifor.uniflix.adapter.FilmeJSONAdapter;
import br.unifor.uniflix.adapter.TvShowJSON;
import br.unifor.uniflix.adapter.TvShowJSONAdapter;
import br.unifor.uniflix.factory.RequestFactory;
import br.unifor.uniflix.model.Filme;
import br.unifor.uniflix.model.TvShow;
import okhttp3.Call;

public class UniFlixFacade {

	public List<TvShow> listTvShow() throws IOException {
		
		String path = "/tv/airing_today";
		RequestFactory rf = new RequestFactory();
		Call call = rf.requestCreate(path);
		okhttp3.Response response = call.execute();
		TvShowJSON tvShowAdapter = new TvShowJSONAdapter();
		JSONObject jsonResponse = new  JSONObject(response.body().string());
	    JSONArray jsonArray = jsonResponse.getJSONArray("results");
	    List<TvShow> tvShows = new ArrayList<>();
	    for (int i = 0; i < jsonArray.length(); ++i) {
	        JSONObject tvShowJson = jsonArray.getJSONObject(i);
	        tvShows.add(tvShowAdapter.getTvShow(tvShowJson));  
	    }
	    return tvShows;
		
	}
	
	
	public List<Filme> listFilme() throws IOException{
		
		String path = "/movie/popular";
    	RequestFactory rf = new RequestFactory();
    	Call call = rf.requestCreate(path);
    	okhttp3.Response response = call.execute();
    	FilmeJSON filmeAdapter = new FilmeJSONAdapter();
    	JSONObject jsonResponse = new JSONObject(response.body().string());
        JSONArray jsonArray = jsonResponse.getJSONArray("results");
        List<Filme> filmes = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            JSONObject movieJson = jsonArray.getJSONObject(i);
            filmes.add(filmeAdapter.getFilme(movieJson));  
        }
    	
		return filmes;
	}
	
}
