package projet.commun;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import projet.data.Categorie;
import projet.data.Compte;
import projet.data.Memo;
import projet.data.Personne;
import projet.data.Service;
import projet.data.Benevole;


@Mapper
public interface IMapper {  
	
	Compte update( @MappingTarget Compte target, Compte source  );
	
	Categorie update( @MappingTarget Categorie target, Categorie source );

	@Mapping( target="categorie", expression="java( source.getCategorie() )" )
	Personne update( @MappingTarget Personne target, Personne source );

	@Mapping( target="categorie", expression="java( source.getCategorie() )" )
	Memo update( @MappingTarget Memo target, Memo source );

	Service update( @MappingTarget Service target, Service source );
	
	Benevole update( @MappingTarget Benevole target, Benevole source );
	
}
