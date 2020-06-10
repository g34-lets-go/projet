package projet.commun;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import projet.data.Categorie;
import projet.data.Compte;
import projet.data.Equipe;
import projet.data.Participant;
import projet.data.Poste;
import projet.data.Benevole;


@Mapper
public interface IMapper {  
	
	Compte update( @MappingTarget Compte target, Compte source  );
	
	Categorie update( @MappingTarget Categorie target, Categorie source );

		
	@Mapping( target="posteBene", expression="java( source.getPosteBene() )" )
	Benevole update( @MappingTarget Benevole target, Benevole source );
	
	Poste update( @MappingTarget Poste target, Poste source );

	Participant update(@MappingTarget Participant target, Participant source);

	Equipe  update(@MappingTarget Equipe target, Equipe source);
}
