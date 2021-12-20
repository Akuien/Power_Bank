package usecases;

import domain.entities.Portfolio;
import domain.exceptions.BankAccountDoesNotExistException;
import domain.exceptions.CustomerDoesNotExistException;
import domain.exceptions.ShareholderDoesNotExistException;
import repositories.PortfolioRepository;

public class ObtainShareholderPortfolio {

        private ValidateShareholder validateShareholder;
        private PortfolioRepository portfolioRepository;

        public ObtainShareholderPortfolio(){
            this.validateShareholder = new ValidateShareholder();
            this.portfolioRepository = new PortfolioRepository();
        }

        public Portfolio execute(long customerSSN) throws Exception {
            boolean shareholderExists = validateShareholder.execute(customerSSN);
            if (!shareholderExists){
                throw new ShareholderDoesNotExistException(customerSSN);
            }
            return portfolioRepository.getPortfolioBySSN(customerSSN);
        }

    }

