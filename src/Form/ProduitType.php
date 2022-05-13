<?php

namespace App\Form;
use App\Entity\Produit ;
use App\Entity\Categorie;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Validator\Constraints\NotBlank;


class ProduitType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nomPdt',TextType::class,[
                'constraints' => new NotBlank(['message' =>'please saisir un nom'])
            ])
            ->add('prix',TextType::class,[
                'constraints' => new NotBlank(['message' =>'please saisir un prix'])
            ])
            ->add('description',TextType::class,[
                'constraints' => new NotBlank(['message' =>'please saisir une description'])
            ])
            ->add('stock',TextType::class,[
                'constraints' => new NotBlank(['message' =>'please donner le stock'])
            ])
            ->add('icone',FileType::class,[
                'label'=> false,
                'mapped'=> false
               // 'required' => false
            ])
            ->add('idCat', EntityType::class, [
                'class'=>Categorie::class,
                'choice_label'=>'nomCat',
                'multiple'=>false,
                'expanded'=>false,
            ])
            ->add('ajouter',SubmitType::class)
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Produit::class,
        ]);
    }
}
