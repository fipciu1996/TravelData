kubectl create namespace postgres
kubectl create -f ..\src\postgres-configmap.yaml
kubectl create -f ..\src\postgres-volumes.yaml
kubectl create -f ..\src\postgres-deployment.yaml
kubectl create -f ..\src\postgres-service.yaml
kubectl get svc postgres
pause