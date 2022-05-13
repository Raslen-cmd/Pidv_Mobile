<?php

namespace App\Controller;
use App\Controller\FileUploader;

use App\Entity\Produit;
use App\Form\ProduitType;
use App\Entity\Categorie;
use App\Form\CategorieType;

use App\Repository\ProduitRepository;
use Symfony\Component\HttpFoundation\File\File;
use MercurySeries\FlashBundle\FlashyNotifier;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
class ProduitController extends AbstractController
{
    /**
     * @Route("/", name="app_produit")
     */
    public function index(NormalizerInterface $Normalizer,ProduitRepository $produitRepository): Response
    {$produits= $this->getDoctrine()->getManager()->getRepository(Produit::class)->findAll();
     $categories= $this->getDoctrine()->getManager()->getRepository(Categorie::class)->findAll();
       /*$produits = $produitRepository->findAll();
        if ($produits) {
            return new JsonResponse($produits, 200);
        } else {
            return new JsonResponse([], 204);
        }*/
        $jsonContent = $Normalizer->normalize($produits, 'json',['groups'=>'post:read']);

       // return $this->render('produit/index.html.twig', ['p'=>$produits,'f'=>$categories,
        //'data'=>$jsonContent,]);
        return new Response(json_encode($jsonContent));
    }
  
 /**
     * @Route("/affiche", name="app_produit")
     */
    public function index2(NormalizerInterface $Normalizer,ProduitRepository $produitRepository): Response
    {  $produits= $this->getDoctrine()->getManager()->getRepository(Produit::class)->findAll();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formated =$serializer->normalize($produits);
        return new JsonResponse($formated);
        
    }
     /**
     * @Route("/produit/addPdt", name="add_produit")
     */
    public function addPdt(Request $request,NormalizerInterface $Normalizer ):JsonResponse
    { /* $prod= new Produit;
       $form=$this-> createForm(ProduitType::class, $prod);
       $form-> handleRequest($request);

       if($form->isSubmitted() && $form->isValid()) {
           
        $file = $prod->getIcone();
        $fileName= md5(uniqid()).'.'.$file;
        
        $em= $this->getDoctrine()->getManager();
        $prod->setIcone($fileName);
        $em->persist($prod);
        $em->flush();
        
        $jsonContent = $Normalizer->normalize($prod, 'json',['groups'=>'post:read']);

       // return $this->redirectToRoute('app_produit');
    }
     // return $this->render('produit/create.html.twig', ['f' => $form->createView() ]);
        return new Response(json_encode($jsonContent));*/
        $produit = new Produit();

        $nomPdt = $request->query->get("nomPdt");
        $prix = $request->query->get("prix");
        $description = $request->query->get("description");
        $stock = $request->query->get("stock");
        //$idCat = $request->query->get("idCat");

    
        $em= $this->getDoctrine()->getManager();

    $produit->setNomPdt($nomPdt);
    $produit->setPrix($prix);
    $produit->setDescription($description);
    $produit->setStock($stock);
    //$produit->setIdCat($idCat);


    $em->persist($produit);
    $em->flush();
    $serializer = new Serializer([new ObjectNormalizer()]);
    $formated =$serializer->normalize($produit);
    return new JsonResponse($formated);

     //   return $this->manage($produit, $request, false);
    }

     /**
     * @Route("/suppPdt/{id}", name="delete_produit")
     */
    public function delete(Request $request,Produit $prod,NormalizerInterface $Normalizer ): Response
    {
        $em= $this->getDoctrine()->getManager();
        $em->remove($prod);
        $em->flush(); 
        $jsonContent = $Normalizer->normalize($prod, 'json',['groups'=>'post:read']);

        return $this->redirectToRoute('app_produit');
        return new Response("Information deleted successfuly".json_encode($jsonContent));    
    }
/**
     * @Route("/suppPdt2/{id}", name="delete_produit")
     */
    public function delete2(Request $request,Produit $prod,NormalizerInterface $Normalizer ): Response
    {$id = $request->get("id");
        $em= $this->getDoctrine()->getManager();
        $produit= $em->getRepository(Produit::Class)->find($id);
        if($produit!=null){
            $em->remove($prod);
            $em->flush();

            $serializer = new Serializer([new ObjectNormalizer()]);
            $formated =$serializer->normalize($produit);
            return new JsonResponse("produit supprimer");
        }
        return new JsonResponse("id produit invalide");

    }
     /**
     * @Route("/modifPdt/{id}", name="update_produit")
     */
    public function update(Request $request,$id,NormalizerInterface $Normalizer ): Response
    {
        $prod = $this->getDoctrine()->getManager()->getRepository(Produit::class)->find($id);
        $form = $this->createForm(ProduitType::class,$prod);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()) {
           
            $file = $prod->getIcone();
            $fileName= md5(uniqid()).'.'.$file;
            
             if($fileName){
                 $prod->setIcone($file);
             }
            $em= $this->getDoctrine()->getManager();
            $prod->setIcone($fileName);
            $em->persist($prod);
            $em->flush();
            return $this->redirectToRoute('app_produit');
        }
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formated =$serializer->normalize($produit);
        return new JsonResponse("produit modifier");

       /* $jsonContent = $Normalizer->normalize($prod, 'json',['groups'=>'post:read']);

        return $this->render('produit/update.html.twig',['f'=>$form->createView()]);
        return new Response("Information update successfuly".json_encode($jsonContent));    
    */ }
/**
     * @Route("/modifPdt2/{id}", name="update_produit")
     
     */
    public function update2(Request $request,$id,NormalizerInterface $Normalizer ): Response
    {
        $em= $this->getDoctrine()->getManager();
        $produit= $this->getDoctrine()->getManager()
        ->getRepository(Produit::class)
        ->find($request->get("id"));

    $produit->setNomPdt($request->get("nomPdt"));
    $produit->setPrix($request->get("prix"));
    $produit->setDescription($request->get("description"));
    $produit->setStock($request->get("stock"));

    $em->persist($produit);
    $em->flush();
    $serializer = new Serializer([new ObjectNormalizer()]);
    $formated =$serializer->normalize($produit);
    return new JsonResponse("produit modifier");
    }
     /**
     * @Route("/detailPdt/{id}", name="detail_produit")
     */
    public function detail($id): Response
    {$produits= $this->getDoctrine()->getManager()->getRepository(Produit::class)->find($id);
        return $this->render('produit/detail.html.twig', ['p'=>$produits
        ]);
    }

   

     /**
     * @Route("/categorie/addCat", name=" add_categorie")
     */
    public function addCat(Request $request ):Response
    {
       return $this->render('categorie/create.html.twig');
    }
}
