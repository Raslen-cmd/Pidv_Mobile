<?php

namespace App\Entity;

use App\Repository\ProduitRepository;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;

/**
 * Produit
 *
 * @ORM\Table(name="produit")
 * @ORM\Entity
 */
class Produit
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_pdt", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */ 
     // @ORM\ManyToOne(targetEntity="App\Entity\Produit",
     // mappedBy="Categorie")
     
    private $idPdt;
/**
     * @Assert\NotBlank(message=" le champ nom doit etre non vide")
     * @Assert\Length(
     *      min = 3,
     *      minMessage=" Entrer un nom au mini de 3 caracteres"
     *
     *     )
     * @ORM\Column(type="string", length=255)
     */

    /**
     * @var string
     *
     * @ORM\Column(name="nom_pdt", type="string", length=30, nullable=false)
     */
    private $nomPdt;
/**
     * @Assert\NotBlank(message=" le champ prix doit etre non vide")
     * @Assert\Length(
     *      min = 0,
     *      minMessage=" Entrer un prix >= 0"
     *
     *     )
     */
    
    /**
     * @var float
     *
     * @ORM\Column(name="prix", type="float", precision=10, scale=0, nullable=false)
     */
    private $prix;

    /**
     * @Assert\NotBlank(message="description doit etre non vide")
     * @Assert\Length(
     *      min = 3,
     *      max = 30,
     *      minMessage = "doit etre >=3 ",
     *      maxMessage = "doit etre <=30" )
     */ 
    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=30, nullable=false)
     */

     
    private $description;

    /**
     * @var int
     *
     * @ORM\Column(name="stock", type="integer", nullable=false)
     */
    private $stock;

    /**
     * @var string
     *
     * @ORM\Column(name="icone", type="string", length=30, nullable=false)
     */
    private $icone;

    /**
     * @var int
     *
     * @ORM\Column(name="id_cat", type="integer", nullable=false)
     */
    private $idCat;

    public function getIdPdt(): ?int
    {
        return $this->idPdt;
    }

    public function getNomPdt(): ?string
    {
        return $this->nomPdt;
    }

    public function setNomPdt(string $nomPdt): self
    {
        $this->nomPdt = $nomPdt;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getStock(): ?int
    {
        return $this->stock;
    }

    public function setStock(int $stock): self
    {
        $this->stock = $stock;

        return $this;
    }

    public function getIcone(): ?string
    {
        return $this->icone;
    }

    public function setIcone(string $icone): self
    {
        $this->icone = $icone;

        return $this;
    }

    public function getIdCat(): ?int
    {
        return $this->idCat;
    }

    public function setIdCat(int $idCat): self
    {
        $this->idCat = $idCat;

        return $this;
    }


}
